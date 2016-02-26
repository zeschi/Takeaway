package com.zes.xiaoxuntakeaway.controller;

import com.zes.xiaoxuntakeaway.bean.MenuTypeCallback;
import com.zes.xiaoxuntakeaway.constant.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zes on 16-2-17.
 */
public class MenuTypeController {


    public static void getMenuTypeListByMerchantId(String merchantId, MenuTypeCallback callback) {
        OkHttpUtils
                .post().addParams("merchantId", merchantId)
                .url(Const.URL_GET_MENU_TYPE_LIST)
                .build()
                .execute(callback);
    }
}
