package com.vipvideo.util;

import android.text.TextUtils;
import android.widget.TextView;

/**
 * author: rsw
 * created on: 2017/7/21 上午11:34
 * description:TextView 非空处理以免程序出现空指针异常直接崩溃
 */
public class TextViewHelper {

    public static void setText(TextView textView, String text) {
        setText(textView, text, "");
    }

    /**
     * 给TextView的setText添加非空校验，避免NPE
     *
     * @param textView
     * @param text
     * @param defaultValue
     */
    public static void setText(TextView textView, String text, String defaultValue) {
        if (text == null || text.isEmpty()) {
            textView.setText(defaultValue);
        } else {
            textView.setText(text);
        }
    }

    public static String isEmpty(String text) {
        return isEmpty(text, "");
    }

    public static String isEmpty(String text, String defaultValue) {
        return !TextUtils.isEmpty(text) ? text : defaultValue;
    }

    public static boolean equals(CharSequence c1, CharSequence... obj) {
        if (c1==null||obj == null || obj.length == 0) {
            return false;
        }
        for (CharSequence s : obj) {
            if (TextUtils.equals(c1, s)) {
                return true;
            }
        }
        return false;
    }
}
