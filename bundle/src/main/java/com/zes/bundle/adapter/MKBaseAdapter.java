package com.zes.bundle.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zes.bundle.bean.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 抽象adapter，所有的adapter继承这个adapter，实现convert方法就行。达到简化代码的目的
 *
 * @param <T> 数据项类型
 * @author zes and benio
 */
public abstract class MKBaseAdapter<T> extends BaseAdapter {
    protected static final String TAG = MKBaseAdapter.class.getSimpleName();

    protected Context mContext;

    protected List<T> mDatas;

    protected int mLayoutResId;

    public MKBaseAdapter(Context context, int layoutId) {
        this(context, null, layoutId);
    }

    /**
     * 构造方法，初始化变量
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public MKBaseAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas == null ? new ArrayList<T>() : datas;
        this.mLayoutResId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getViewHolder(mContext, convertView, parent, mLayoutResId, position);
        convert(holder, mDatas.get(position));
        return holder.getConvertView();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 添加数据
     *
     * @param elem
     */
    public void add(T elem) {
        mDatas.add(elem);
        notifyDataSetChanged();
    }

    public void addAll(List<T> elem) {
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {
        set(mDatas.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        mDatas.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        mDatas.clear();
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return mDatas.contains(elem);
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void setData(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    /**
     * 所有逻辑代码在子类中的这个方法中实现。
     *
     * @param holder
     * @param data
     */
    public abstract void convert(ViewHolder holder, T data);
}
