package com.zes.xiaoxuntakeaway.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by zes on 16-2-17.
 */
public abstract class MenuCallback extends Callback<ResultDataInfo<List<Menu>>> {

    @Override
    public ResultDataInfo<List<Menu>> parseNetworkResponse(Response response) throws Exception {

        String string = response.body().string();
        ResultDataInfo<List<Menu>> resultDataInfo = new Gson().fromJson(string, new TypeToken<ResultDataInfo<List<Menu>>>() {
        }.getType());

        return resultDataInfo;
    }
}
