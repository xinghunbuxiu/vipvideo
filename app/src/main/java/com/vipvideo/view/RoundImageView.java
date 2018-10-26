package com.vipvideo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.vipvideo.R;


/**
 * 圆形的ImageView
 */
public class RoundImageView extends AppCompatImageView {
    private int cornerRadius;
    private float topLeftRadius;
    private float bottomRightRadius;
    private float bottomLeftRadius;
    private float topRightRadius;
    private Paint paint;
    private RectF layer;  //内容区域
    public Path mClipPath;// 剪裁区域路径

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyle, 0);
        cornerRadius =
                a.getDimensionPixelSize(R.styleable.RoundImageView_riv_corner_radius, -1);

        topLeftRadius = a.getDimensionPixelSize(R.styleable.RoundImageView_riv_corner_radius_top_left, -1);
        topRightRadius =
                a.getDimensionPixelSize(R.styleable.RoundImageView_riv_corner_radius_top_right, -1);
        bottomLeftRadius =
                a.getDimensionPixelSize(R.styleable.RoundImageView_riv_corner_radius_bottom_left, -1);
        bottomRightRadius =
                a.getDimensionPixelSize(R.styleable.RoundImageView_riv_corner_radius_top_right, -1);

        a.recycle();
        init(context, attrs, defStyle);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        if (paint == null) {
            paint = new Paint();
            mClipPath = new Path();
            paint.setAntiAlias(true);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        layer = new RectF(0, 0, w, h);
        RectF pathRect = new RectF(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        mClipPath.reset();
        mClipPath.addRoundRect(pathRect, cornerRadius, cornerRadius, Path.Direction.CW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int count = canvas.saveLayer(layer, null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        canvas.drawPath(mClipPath, paint);
        canvas.restoreToCount(count);
    }
}
