package com.zes.xiaoxuntakeaway.adapter;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.snappydb.SnappydbException;
import com.zes.bundle.adapter.MKBaseAdapter;
import com.zes.bundle.bean.ViewHolder;
import com.zes.bundle.utils.GsonUtil;
import com.zes.xiaoxuntakeaway.R;
import com.zes.xiaoxuntakeaway.bean.Menu;
import com.zes.xiaoxuntakeaway.bean.Merchant;
import com.zes.xiaoxuntakeaway.bean.MerchantCallback;
import com.zes.xiaoxuntakeaway.bean.Order;
import com.zes.xiaoxuntakeaway.bean.ResultDataInfo;
import com.zes.xiaoxuntakeaway.controller.MerchantController;
import com.zes.xiaoxuntakeaway.database.DbHelper;

import java.util.List;

import okhttp3.Call;

/**
 * Created by zes on 16-1-20.
 */
public class OrderAdapter extends MKBaseAdapter<Order> {


    /**
     * 构造方法，初始化变量
     *
     * @param context
     * @param datas
     * @param layoutId
     */
    public OrderAdapter(Context context, List<Order> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    /**
     * 所有逻辑代码在子类中的这个方法中实现。
     *
     * @param holder
     * @param data
     */
    @Override
    public void convert(final ViewHolder holder, final Order data) {
        List<Menu> menus = GsonUtil.getGson().fromJson(data.getMenu_list(), new TypeToken<List<Menu>>() {
        }.getType());
        Merchant merchant = null;
        try {
            merchant = DbHelper.getSnappyDb().get(data.getMerchant_id() + "_merchant", Merchant.class);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }


        if (merchant != null) {
            holder.setText(R.id.tv_order_merchant_name, merchant.getMerchant_name());
            holder.setCircleImageByUrl(R.id.iv_order_merchant, merchant.getMerchant_portrait(), R.drawable.pictures_no);
        } else {
            MerchantController.getMerchantById(data.getMerchant_id(), new MerchantCallback() {
                @Override
                public void onError(Call call, Exception e) {

                }

                @Override
                public void onResponse(ResultDataInfo<Merchant> response) {
                    if (response == null || response.getData() == null)
                        return;
                    Merchant merchant = response.getData();
                    try {
                        DbHelper.getSnappyDb().put(merchant.getMerchant_id() + "_merchant", merchant);
                    } catch (SnappydbException e) {
                        e.printStackTrace();
                    }
                    holder.setText(R.id.tv_order_merchant_name, merchant.getMerchant_name());
                    holder.setCircleImageByUrl(R.id.iv_order_merchant, merchant.getMerchant_portrait(), R.drawable.pictures_no);

                }
            });
        }
    }
}
