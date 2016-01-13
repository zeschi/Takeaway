package com.zes.bundle.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zes.bundle.bean.RecycleViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 抽象adapter，所有的adapter继承这个adapter，实现convertView方法就行。达到简化代码的目的
 * Created by zes on 15-10-12.
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;
    protected int mLayoutId;

    public BaseRecycleAdapter(Context context, int layoutId) {
        this(context, null, layoutId);
    }

    public BaseRecycleAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mDatas = datas == null ? new ArrayList<T>() : datas;
        this.mLayoutId = layoutId;
    }


    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleViewHolder holder = new RecycleViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false),mContext);
        return holder;
    }


    @Override
    public void onBindViewHolder(final RecycleViewHolder holder, int position) {
        convertView(holder, mDatas.get(position), position);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder,v, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.itemView, pos);

                    return false;
                }
            });


        }


    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
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

    public void addData(int position, T data) {
        mDatas.add(position, data);
        notifyItemInserted(position);
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
        notifyItemChanged(index);
    }

    public void remove(T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);

        //  notifyDataSetChanged();
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
     * 所有子类的逻辑代码的实现
     *
     * @param holder
     * @param data
     * @param position
     */
    protected abstract void convertView(RecycleViewHolder holder, T data, int position);

    public interface OnItemClickListener {
        void onItemClick(RecycleViewHolder holder, View view, int position);

        void onItemLongClick(View view, int position);

    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickLitener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    /**
     * @param view
     * @param text
     */
    protected void showSnackBar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

}
