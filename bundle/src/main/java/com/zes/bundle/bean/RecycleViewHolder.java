package com.zes.bundle.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zes.bundle.R;
import com.zes.bundle.adapter.BaseRecycleAdapter;
import com.zes.bundle.view.RecycleImageView;

import java.io.File;


/**
 * Created by zes on 15-10-12.
 */
public class RecycleViewHolder extends RecyclerView.ViewHolder {


    private SparseArray<View> mViews;
    private Context mContext;
    protected View mConvertView;

    public RecycleViewHolder(View itemView, Context context) {
        super(itemView);
        mConvertView = itemView;
        mContext = context;
        this.mViews = new SparseArray<View>();
    }

    /**
     * 获取根视图
     *
     * @return
     */
    public View getRootView() {
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
    public RecycleViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public RecycleViewHolder setText(int viewId, Number num) {
        return setText(viewId, String.valueOf(num));
    }

    public RecycleViewHolder appendText(int viewId, String text) {
        if (!TextUtils.isEmpty(text)) {
            TextView tv = getView(viewId);
            tv.append(text);
        }
        return this;
    }

    public RecycleViewHolder appendText(int viewId, Number num) {
        return appendText(viewId, String.valueOf(num));
    }

    /**
     * 设置TextView的text颜色
     *
     * @param viewId
     * @param textColor
     * @return
     */
    public RecycleViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public RecycleViewHolder setTextColorResource(int viewId, int textColorRes) {
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
    public RecycleViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bm);
        return this;
    }

    public RecycleViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);

        return this;
    }

    public RecycleViewHolder setRecycleAdapter(int viewId, Context context, BaseRecycleAdapter adapter) {
        RecyclerView recyclerView = getView(viewId);
        // recyclerView.addItemDecoration(new DividerGridItemDecoration(context));
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        recyclerView.setAdapter(adapter);
        return this;
    }

    /**
     * ImageView设置为本地图片
     *
     * @param viewId           viewId
     * @param filePath         本地图片地址
     * @param errorResId       error图片,0则不设置
     * @param placeholderResId 占位图，0则不设置
     * @return
     */
    public RecycleViewHolder setImageByFilePath(int viewId, String filePath, int placeholderResId, int errorResId) {
        RecycleImageView iv = getView(viewId);
//        Picasso.with(mContext).load(new File(filePath))
//                .error(R.drawable.pictures_no).
//                config(Bitmap.Config.RGB_565).transform(new CropSquareTransformation()) .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(iv);

        Glide.with(mContext).load(new File(filePath)).error(R.drawable.pictures_no).into(iv);
        return this;
    }

    /**
     * 网络图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public RecycleViewHolder setImageByUrl(int viewId, String url) {
        ImageView iv = getView(viewId);
        Glide.with(mContext).load(url).into(iv);
        return this;
    }

    /**
     * 设置图片文件路径
     *
     * @param viewId
     * @param filePath
     * @return
     */
    public RecycleViewHolder setImageByFilePath(int viewId, String filePath) {
        setImageByFilePath(viewId, filePath, 0, 0);
        return this;
    }


}
