package com.zes.xiaoxuntakeaway.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zes.bundle.utils.MKLog;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by zes on 16-3-1.
 */
public abstract class OrderListCallback extends Callback<ResultDataInfo<List<Order>>> {

    @Override
    public ResultDataInfo<List<Order>> parseNetworkResponse(Response response) throws Exception {

        String string = response.body().string();
//        if (string.contains("\\\"")) {
//            string = string.replace("\\\"", "\"");
//
//        }
//        if (string.contains("&")) {
//            string = string.replace("&#039;", "'");
//            string = string.replace("&quot;", "\"");
//            string = string.replace("&lt;", "<");
//            string = string.replace("&gt;", ">");
//            string = string.replace("&amp;", "&");
//        }
//
         MKLog.e("string" + string.toString());

        ResultDataInfo<List<Order>> resultDataInfo = new Gson().fromJson(string, new TypeToken<ResultDataInfo<List<Order>>>() {
        }.getType());
        MKLog.e("resultDataInfo" + resultDataInfo.getData().toString());
        return resultDataInfo;
    }
}
