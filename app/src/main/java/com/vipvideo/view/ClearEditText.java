package com.vipvideo.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.vipvideo.R;

/**
 * author: rsw
 * created on: 2018/10/21 下午2:40
 * description:
 */

public class ClearEditText extends carbon.widget.EditText {

    private Drawable drawable;
    private Context context;

    public ClearEditText(Context context) {
        super(context);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ClearEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.context=context;
        drawable = getResources().getDrawable(R.mipmap.ai_account_register_clear);
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        setClearIconVisible(hasFocus()&&charSequence.length() > 0);
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        setClearIconVisible(length()>0);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            Drawable drawable = getCompoundDrawables()[2];
            if (drawable != null && motionEvent.getX() <= ((float) (getWidth() - getPaddingRight())) && motionEvent.getX() >= ((float) ((getWidth() - getPaddingRight()) - drawable.getBounds().width()))) {
                setText("");
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    private void setClearIconVisible(boolean z) {
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[1], z ? this.drawable : null, getCompoundDrawables()[3]);
    }
}
