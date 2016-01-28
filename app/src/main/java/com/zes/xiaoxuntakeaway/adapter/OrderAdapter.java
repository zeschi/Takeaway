package com.zes.xiaoxuntakeaway.adapter;

import android.content.Context;

import com.zes.bundle.adapter.MKBaseAdapter;
import com.zes.bundle.bean.ViewHolder;
import com.zes.xiaoxuntakeaway.R;

import java.util.List;

/**
 * Created by zes on 16-1-20.
 */
public class OrderAdapter extends MKBaseAdapter<String> {

    /**
     * 构造方法，初始化变量
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public OrderAdapter(Context context, List<String> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    /**
     * 所有逻辑代码在子类中的这个方法中实现。
     *
     * @param holder
     * @param data
     */
    @Override
    public void convert(ViewHolder holder, String data) {
        holder.setCircleImageByUrl(R.id.iv_order_merchant, "http://pic25.nipic.com/20121206/6789926_185118320000_2.jpg", R.drawable.pictures_no);
    }
}
