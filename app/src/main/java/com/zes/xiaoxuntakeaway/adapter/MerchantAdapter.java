package com.zes.xiaoxuntakeaway.adapter;

import android.content.Context;

import com.zes.bundle.adapter.MKBaseAdapter;
import com.zes.bundle.bean.ViewHolder;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.bean.Merchant;

import java.util.List;

/**
 * Created by zes on 16-1-15.
 */
public class MerchantAdapter extends MKBaseAdapter<Merchant> {

    /**
     * 构造方法，初始化变量
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public MerchantAdapter(Context context, List<Merchant> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    /**
     * 所有逻辑代码在子类中的这个方法中实现。
     *
     * @param holder
     * @param data
     */
    @Override
    public void convert(final ViewHolder holder, Merchant data) {
        holder.setText(R.id.tv_merchant_month_sale, "月售" + data.getMerchant_month_sale() + "份");
        holder.setText(R.id.tv_merchant_name, data.getMerchant_name());
        holder.setText(R.id.tv_merchant_delivery_price, "配送费" + data.getMerchant_delivery_price() + "元");
        holder.setText(R.id.tv_merchant_start_price, "起送价" + data.getMerchant_start_price() + "元");
        holder.setText(R.id.tv_merchant_discount_details, "在线支付" + data.getMerchant_discount_details());
        holder.setImageByUrl(R.id.iv_merchant_pic, data.getMerchant_portrait());


    }


}
