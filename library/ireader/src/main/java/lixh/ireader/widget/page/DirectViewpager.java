package lixh.ireader.widget.page;

/**
 * Created by mobisys on 10/10/2016.
 */


import android.content.Context;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DirectViewpager extends ViewPager {
    private static final String TAG = "ViewPager";
    private Direction direction;
    private RectF mCenterRect = null;
    private RectF mLeft = null;
    //点击监听
    private DirectViewpager.TouchListener mTouchListener;

    public enum Direction {
        HORIZONTAL,
        VERTICAL,
    }

    public interface TouchListener {
        boolean onTouch();

        void center();

        void prePage();

        void nextPage();
    }

    public DirectViewpager(Context context) {
        this(context, null);
    }

    public DirectViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchListener getTouchListener() {
        return mTouchListener;
    }

    public void setTouchListener(TouchListener mTouchListener) {
        this.mTouchListener = mTouchListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (direction == Direction.VERTICAL) {
            boolean intercept = super.onInterceptTouchEvent(swapEvent(ev));
            swapEvent(ev);
            return intercept;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        makeClickEvent(ev);
        return super.onTouchEvent(swapEvent(ev));
    }

    private boolean ifCanTouch() {
        if (mTouchListener != null) {
            return mTouchListener.onTouch();
        }
        return false;
    }

    private MotionEvent swapEvent(MotionEvent ev) {
        float width = getWidth();
        float height = getHeight();
        if (direction == Direction.VERTICAL) {
            float swappedX = (ev.getY() / height) * width;
            float swappedY = (ev.getX() / width) * height;
            ev.setLocation(swappedX, swappedY);
        }
        return ev;
    }

    public void makeClickEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        float width = getWidth();
        float height = getHeight();
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (ifCanTouch()) {
                return;
            }
            //设置中间区域范围
            if (mCenterRect == null) {
                mCenterRect = new RectF(width / 3, height / 3,
                        width * 2 / 3, height * 2 / 3);
            }
            if (mLeft == null) {
                mLeft = new RectF(0, 0,
                        width / 2, height);
            }
            if (mTouchListener != null) {
                if (mCenterRect.contains(x, y)) {
                    mTouchListener.center();
                } else if (mLeft.contains(x, y)) {
                    mTouchListener.prePage();
                } else {
                    mTouchListener.nextPage();
                }
            }
        }
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        if (direction == Direction.VERTICAL) {
            setPageTransformer(true, new DefaultTransFormer());
        }
    }

    public class DefaultTransFormer implements ViewPager.PageTransformer {

        @Override
        public void transformPage(View view, float position) {
            float alpha = 0;
            if (0 <= position && position <= 1) {
                alpha = 1 - position;
            } else {
                alpha = position + 1;
            }
            view.setAlpha(alpha);
            float transX = view.getWidth() * -position;
            view.setTranslationX(transX);
            float transY = view.getHeight() * position;
            view.setTranslationY(transY);
        }
    }
}
