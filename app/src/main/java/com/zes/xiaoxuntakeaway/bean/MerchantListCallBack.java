package com.zes.xiaoxuntakeaway.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by zes on 16-2-16.
 */
public abstract class MerchantListCallBack extends Callback<ResultDataInfo<List<Merchant>>> {

    @Override
    public ResultDataInfo<List<Merchant>> parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        ResultDataInfo<List<Merchant>> resultDataInfo = new Gson().fromJson(string, new TypeToken<ResultDataInfo<List<Merchant>>>() {
        }.getType());
    //    MKLog.e(resultDataInfo.toString());

        return resultDataInfo;
    }
}
