package com.zes.bundle.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 拖动控件并显示遮罩
 */
public class DragMaskLayout extends FrameLayout {
    private ViewDragHelper mDragger;

    private View mDragView;
    private View mMaskView;
    private Context mContext;
    private Point mDragViewOriginPos = new Point();
    //初始化当前控件的位置
    private int currentX = 0;
    private int currentY = 0;
    //记录上一次控件的位置
    private int previousX = 0;
    private int previousY = 0;

    public DragMaskLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }


            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                //mEdgeTrackerView禁止直接移动
                return child == mDragView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return child.getLeft();
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }


            //手指释放的时候回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //mAutoBackView手指释放时可以自动回去
//                if (releasedChild == mAutoBackView) {
//                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
//                    invalidate();
//                }
                if (releasedChild == mDragView) {
                    if (yvel > 0 || mDragView.getTop() < 0) {
                        mDragger.settleCapturedViewAt(mDragViewOriginPos.x, mDragViewOriginPos.y);
                        mMaskView.setVisibility(INVISIBLE);
                        invalidate();
                    }

                }
            }

        });

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        currentX = (int) event.getX();  //获取当前X坐标
        currentY = (int) event.getY();  //获取当前Y坐标

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //按下控件时
                previousX = currentX;  //记录上次X,Y坐标
                previousY = currentY;
                break;
            case MotionEvent.ACTION_MOVE: //开始移动控件
                int detelX = currentX - previousX;
                int detelY = currentY - previousY;
                if (detelY > 10) {
                    mMaskView.setVisibility(VISIBLE);
                }

                previousX = currentX - detelX; //记录上一次的X，Y坐标
                previousY = currentY - detelY;
                break;

            case MotionEvent.ACTION_UP: //触摸事件结束，触摸离开屏幕

                break;

            case MotionEvent.ACTION_CANCEL: //取消触摸事件
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mDragViewOriginPos.x = mDragView.getLeft();
        mDragViewOriginPos.y = mDragView.getTop();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() >= 1) {
            mDragView = getChildAt(1);
            mMaskView = getChildAt(0);
        }

    }

    private boolean mIsDisallowIntercept = false;

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // keep the info about if the innerViews do requestDisallowInterceptTouchEvent
        mIsDisallowIntercept = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // the incorrect array size will only happen in the multi-touch scenario.
        if (ev.getPointerCount() > 1 && mIsDisallowIntercept) {
            requestDisallowInterceptTouchEvent(false);
            boolean handled = super.dispatchTouchEvent(ev);
            requestDisallowInterceptTouchEvent(true);
            return handled;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }
}
