package com.zes.xiaoxuntakeaway.controller;

import com.zes.xiaoxuntakeaway.bean.MerchantCallback;
import com.zes.xiaoxuntakeaway.bean.MerchantListCallBack;
import com.zes.xiaoxuntakeaway.constant.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zes on 16-2-16.
 */
public class MerchantController {

    /**
     * 获取商家列表信息
     *
     * @param callback
     */
    public static void getMerchantList(MerchantListCallBack callback) {
        OkHttpUtils
                .post()
                .url(Const.URL_GET_MERCHANT_LIST)
                .build()
                .execute(callback);
    }

    public static void getMerchantById(String merchantId, MerchantCallback callback) {
        OkHttpUtils
                .post().addParams("merchantId", merchantId)
                .url(Const.URL_GET_MERCHANT_INFO)
                .build()
                .execute(callback);
    }


}
