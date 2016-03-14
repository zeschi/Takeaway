package com.zes.xiaoxuntakeaway.controller;

import com.zes.xiaoxuntakeaway.bean.Address;
import com.zes.xiaoxuntakeaway.bean.ResultDataStringCallBack;
import com.zes.xiaoxuntakeaway.constant.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by zes on 16-3-13.
 */
public class AddressController {


    public static void saveAddress(Address address, ResultDataStringCallBack callBack) {

        OkHttpUtils
                .post()
                .url(Const.URL_SAVE_USER_ADDRESS)
                .addParams("userId", address.getUserId())
                .addParams("addressReceipt", address.getReceiptAddress())
                .addParams("userRealName", address.getUserRealName())
                .addParams("addressDoorplate", address.getDoorPlate())
                .addParams("userPhone", address.getUserPhone())
                .build()
                .execute(callBack);
    }

    public static void changeAddress(Address address, String addressId, ResultDataStringCallBack callBack) {

        OkHttpUtils
                .post()
                .url(Const.URL_CAHNGE_USER_ADDRESS)
                .addParams("addressId", addressId)
                .addParams("addressReceipt", address.getReceiptAddress())
                .addParams("userRealName", address.getUserRealName())
                .addParams("addressDoorplate", address.getDoorPlate())
                .addParams("userPhone", address.getUserPhone())
                .build()
                .execute(callBack);
    }

}
