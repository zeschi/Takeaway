package com.zes.bundle.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可点击左右图标的EditText
 *
 * @author Benio
 */
public class ClickableEditText extends AppCompatEditText {
    private static final String TAG = ClickableEditText.class.getSimpleName();
    /**
     * 右边按钮的引用
     */
    protected Drawable mDrawableRight;

    /**
     * 左边按钮的引用
     */
    protected Drawable mDrawableLeft;

    public ClickableEditText(Context context) {
        this(context, null);
    }

    public ClickableEditText(Context context, AttributeSet attrs) {
        // 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClickableEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs) {
        /**
         * getCompoundDrawables()返回Drawable[]数组，数组内容 {dr.mDrawableLeft,
         * dr.mDrawableTop, dr.mDrawableRight, dr.mDrawableBottom}
         * 获取EditText的Drawable
         */
        mDrawableLeft = getCompoundDrawables()[0];
        mDrawableRight = getCompoundDrawables()[2];

        setDrawableBound(mDrawableLeft);
        setDrawableBound(mDrawableRight);
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
     * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     * <p>
     * event.getX() 获取相对应自身左上角的X坐标
     * <p>
     * event.getY() 获取相对应自身左上角的Y坐标
     * <p>
     * getWidth() 获取控件的宽度
     * <p>
     * getTotalPaddingRight() 获取删除图标左边缘到控件右边缘的距离
     * <p>
     * getPaddingRight() 获取删除图标右边缘到控件右边缘的距离
     * <p>
     * getWidth() - getTotalPaddingRight() 计算删除图标左边缘到控件左边缘的距离
     * <p>
     * getWidth() - getPaddingRight() 计算删除图标右边缘到控件左边缘的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // 判断右边图标是否为空
            if (mDrawableRight != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    // 处理点击事件
                    onDrawableRightClick();
                }
            }

            if (mDrawableLeft != null) {
                boolean touchable = event.getX() > getPaddingLeft()
                        && event.getX() < getTotalPaddingLeft();
                if (touchable) {
                    // 处理点击事件
                    onDrawableLeftClick();
                }
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * Specify a bounding rectangle for the Drawable. This is where the drawable
     * will draw when its draw() method is called.
     *
     * @param drawable
     */
    protected void setDrawableBound(Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
        }
    }

    /**
     * 点击右边图标时回调
     */
    public boolean onDrawableRightClick() {
        // Log.i(TAG, "onDrawableRightClick");
        return false;
    }

    /**
     * 点击左边图标时回调
     */
    public boolean onDrawableLeftClick() {
        // Log.i(TAG, "onDrawableLeftClick");
        return false;
    }

    /**
     * 绘制右边的图标
     *
     * @param drawable
     */
    protected void setDrawableRight(Drawable drawable) {
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], drawable, getCompoundDrawables()[3]);
    }

    /**
     * 绘制左边的图标
     *
     * @param drawable
     */
    protected void setDrawableLeft(Drawable drawable) {
        setCompoundDrawables(drawable, getCompoundDrawables()[1],
                getCompoundDrawables()[2], getCompoundDrawables()[3]);
    }

    /**
     * 设置右边的图标可见性
     *
     * @param visible
     */
    public void setRightIconVisible(boolean visible) {
        setDrawableRight(visible ? mDrawableRight : null);
    }

    /**
     * 设置左边的图标可见性，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    public void setLeftIconVisable(boolean visible) {
        setDrawableLeft(visible ? mDrawableLeft : null);
    }

}