package com.zes.bundle.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zes.bundle.R;
import com.zes.bundle.utils.GlideCircleTransform;


/**
 * ViewHolder 后期可以根据开发添加一些方法
 *
 * @author zes and hyman
 */
public class ViewHolder {

    private SparseArray<View> mViews;
    private Context mContext;
    private View mConvertView;
    private int mPosition;

//    public ViewHolder(View itemView) {
//        super(itemView);
//    }


    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mContext = context;
        this.mViews = new SparseArray<View>();
        this.mConvertView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        this.mConvertView.setTag(R.id.viewholder, this);
    }

    public ViewHolder(Context context, ViewGroup parent, int layoutId) {
        this(context, parent, layoutId, -1);
    }

    /**
     * 创建ViewHolder
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (null == convertView || null == convertView.getTag(R.id.viewholder)) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            return (ViewHolder) convertView.getTag(R.id.viewholder);
        }
    }

    /**
     * 创建ViewHolder,position默认为-1
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId) {
        return getViewHolder(context, convertView, parent, layoutId, -1);
    }

    /**
     * 获取viewholder对应的position
     *
     * @return
     */
    public int getPosition() {
        return mPosition;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 根据输入的viewId，初始化view，将初始化后的view存入SparseArray类型的mViews中
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * TextView中设置text值，可调用此方法，也可以链式编程。
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setText(int viewId, Number num) {
        return setText(viewId, String.valueOf(num));
    }

    public ViewHolder appendText(int viewId, String text) {
        if (!TextUtils.isEmpty(text)) {
            TextView tv = getView(viewId);
            tv.append(text);
        }
        return this;
    }

    public ViewHolder appendText(int viewId, Number num) {
        return appendText(viewId, String.valueOf(num));
    }

    /**
     * 设置TextView的text颜色
     *
     * @param viewId
     * @param textColor
     * @return
     */
    public ViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public ViewHolder setTextColorResource(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    /**
     * 只适用于imageview及其子类
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bm);
        return this;
    }
//

    /**
     * ImageView设置为网络图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url) {
        return setImageByUrl(viewId, url, 0, 0);
    }

    public ViewHolder setImageByUrl(int viewId, String url, int placeholderResId) {
        return setImageByUrl(viewId, url, placeholderResId, 0);
    }

    public ViewHolder setImageByUrl(int viewId, String url, int placeholderResId, int errorResId) {
        ImageView iv = getView(viewId);
        Glide.with(mContext).load(url).placeholder(placeholderResId).error(errorResId).into(iv);
        // ImageLoader.load(mContext, iv, url, placeholderResId, errorResId);
        return this;
    }

    public ViewHolder setCircleImageByUrl(int viewId, String url, int placeholderResId) {
        return setCircleImageByUrl(viewId, url, placeholderResId, 0);
    }

    /**
     * 加载圆形图片
     *
     * @param viewId
     * @param url
     * @param placeholderResId
     * @param errorResId
     * @return
     */
    public ViewHolder setCircleImageByUrl(int viewId, String url, int placeholderResId, int errorResId) {
        ImageView iv = getView(viewId);
        Glide.with(mContext).load(url).transform(new GlideCircleTransform(mContext)).placeholder(placeholderResId).error(errorResId).into(iv);
        return this;
    }
//    public ViewHolder setCropImageByUrl(int viewId, String url, int placeholderResId, int errorResId) {
//        CropImageView iv = getView(viewId);
//        Glide.with(mContext).load(url).placeholder(placeholderResId).error(errorResId).into(iv);
//        // ImageLoader.load(mContext, iv, url, placeholderResId, errorResId);
//        return this;
//    }
//
//    /**
//     * ImageView设置为本地图片
//     *
//     * @param viewId           viewId
//     * @param filePath         本地图片地址
//     * @param errorResId       error图片,0则不设置
//     * @param placeholderResId 占位图，0则不设置
//     * @return
//     */
//    public ViewHolder setImageByFilePath(int viewId, String filePath, int placeholderResId, int errorResId) {
//        ImageView iv = getView(viewId);
//        ImageLoader.load(mContext, iv, filePath, placeholderResId, errorResId);
//        return this;
//    }
//
//    public ViewHolder setImageByFilePath(int viewId, String filePath) {
//        setImageByFilePath(viewId, filePath, 0, 0);
//        return this;
//    }

    /**
     * 设置ImageView的Image
     *
     * @param viewId
     * @param resId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 设置ImageView的drawable
     *
     * @param viewId
     * @param drawable
     * @return
     */
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView iv = getView(viewId);
        iv.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param viewId
     * @param color
     * @return
     */
    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 适用于所有view设置背景图片
     *
     * @param viewId
     * @param resId  图片资源的ID
     * @return
     */
    public ViewHolder setBackgroundResource(int viewId, int resId) {
        View iv = getView(viewId);
        iv.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置View的可见性
     *
     * @param viewId
     * @param visible true，可见
     * @return
     */
    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置View的监听器
     *
     * @param viewId
     * @param listener
     * @return
     */
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置View的透明度
     *
     * @param viewId
     * @param value
     * @return
     */
    public ViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

}
