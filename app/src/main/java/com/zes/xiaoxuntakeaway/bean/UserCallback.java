package com.zes.xiaoxuntakeaway.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by zes on 16-2-18.
 */
public abstract class UserCallback extends Callback<ResultDataInfo<User>> {

    @Override
    public ResultDataInfo<User> parseNetworkResponse(Response response) throws Exception {

        String string = response.body().string();
        ResultDataInfo<User> resultDataInfo = new Gson().fromJson(string, new TypeToken<ResultDataInfo<User>>() {
        }.getType());
     //   MKLog.e("user", resultDataInfo.getData().getUser_account() + "-----");

        return resultDataInfo;
    }
}
