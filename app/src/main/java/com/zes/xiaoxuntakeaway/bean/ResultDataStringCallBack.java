package com.zes.xiaoxuntakeaway.bean;

import com.google.gson.Gson;
import com.zes.bundle.utils.MKLog;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by zes on 16-2-15.
 */
public abstract class ResultDataStringCallBack extends Callback<ResultDataInfo<String>> {

    @Override
    public ResultDataInfo<String> parseNetworkResponse(Response response) throws Exception {

        String string = response.body().string();
        MKLog.e(string);
        ResultDataInfo<String> resultDataInfo = new Gson().fromJson(string, ResultDataInfo.class);
      //  MKLog.e(resultDataInfo.toString());

        return resultDataInfo;
    }
}
