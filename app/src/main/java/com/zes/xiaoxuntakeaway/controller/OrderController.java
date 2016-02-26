package com.zes.xiaoxuntakeaway.controller;

import com.zes.xiaoxuntakeaway.bean.ResultDataStringCallBack;
import com.zes.xiaoxuntakeaway.constant.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zes on 16-2-21.
 */
public class OrderController {

    public static void createOrder(String userId, String merchantId, String menuListData, ResultDataStringCallBack callBack) {

        OkHttpUtils
                .post()
                .url(Const.URL_CREATE_ORDER)
                .addParams("userId", userId)
                .addParams("merchantId", merchantId)
                .addParams("menuListData", menuListData)
                .build()
                .execute(callBack);


    }
}
