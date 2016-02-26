package com.zes.xiaoxuntakeaway.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zes.bundle.utils.MKLog;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by zes on 16-2-19.
 */
public abstract class MerchantCallback extends Callback<ResultDataInfo<Merchant>> {

    @Override
    public ResultDataInfo<Merchant> parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        MKLog.e("re", string);
        ResultDataInfo<Merchant> resultDataInfo = new Gson().fromJson(string, new TypeToken<ResultDataInfo<Merchant>>() {
        }.getType());

        MKLog.e(resultDataInfo.toString());
        //MKLog.e("merchant", resultDataInfo.getData().getMerchant_address());

        return resultDataInfo;
    }
}
