package com.zes.xiaoxuntakeaway.controller;

import com.zes.xiaoxuntakeaway.bean.ResultDataStringCallBack;
import com.zes.xiaoxuntakeaway.bean.UserCallback;
import com.zes.xiaoxuntakeaway.constant.Const;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * 用户信息管理
 * Created by zes on 16-2-15.
 */
public class UserController {

    /**
     * 登录
     *
     * @param account
     * @param pwd
     * @param callback
     */
    public static void login(String account, String pwd, UserCallback callback) {
        OkHttpUtils
                .post()
                .url(Const.URL_LOGIN)
                .addParams("account", account)
                .addParams("pwd", pwd)
                .build()
                .execute(callback);

    }

    /**
     * 注册
     *
     * @param account
     * @param pwd
     * @param callback
     */
    public static void register(String account, String pwd, ResultDataStringCallBack callback) {
        OkHttpUtils
                .post()
                .url(Const.URL_REGISTER)
                .addParams("account", account)
                .addParams("pwd", pwd)
                .build()
                .execute(callback);
    }

    /**
     * 获得验证码
     *
     * @param callback
     */
    public static void getVerificationCode(ResultDataStringCallBack callback) {
        OkHttpUtils
                .post()
                .url(Const.URL_GET_VERIFICATION_CODE)
                .build()
                .execute(callback);
    }


}
