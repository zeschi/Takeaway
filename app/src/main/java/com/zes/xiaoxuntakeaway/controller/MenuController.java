package com.zes.xiaoxuntakeaway.controller;

import com.zes.xiaoxuntakeaway.bean.MenuCallback;
import com.zes.xiaoxuntakeaway.constant.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zes on 16-2-17.
 */
public class MenuController {

    public static void getMenuListByMerchantId(String merchantId, MenuCallback callback) {
        OkHttpUtils
                .post().addParams("merchantId", merchantId)
                .url(Const.URL_GET_MENU_LIST)
                .build()
                .execute(callback);
    }

}
