package com.zes.bundle.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.zes.bundle.R;


public class ClearEditText extends ClickableEditText {

    private boolean isVisible = true;

    public ClearEditText(Context context) {
        super(context);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // 如果没有设置清除图标，默认显示btn_clear图标
        if (null == mDrawableRight) {
            mDrawableRight = getResources().getDrawable(R.drawable.btn_clear);
            setDrawableBound(mDrawableRight);
        }

        // 默认不显示右边的清除图标
        setRightIconVisible(false);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (isFocused()) {
                    // 输入长度大于0才显示图标
                    //setRightIconVisible(s.length() > 0 && isVisible);
                    setRightIconVisible(s.length() > 0);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }

    /**
     * 清除editText
     */
    public void clearText() {
        setText(null);
    }

//    /**
//     * 设置clear图标可见性
//     *
//     * @param visible
//     */
//    public void setClearIconVisible(boolean visible) {
//        isVisible = visible;
//        invalidate();
//    }

    @Override
    public boolean onDrawableRightClick() {
        // 点击图标，清除文字
        clearText();
        return super.onDrawableRightClick();
    }

}
