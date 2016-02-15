package com.zes.xiaoxuntakeaway.constant;

/**
 * Created by zes on 16-2-15.
 */
public class Const {

    /**
     * 云端服务器
     */
    public static final String URL_BASE = "http://1.takeawaybyzes.applinzi.com/index.php?s=/home/";


    public static String URL_LOGIN = URL_BASE + "user/login";

    public static String URL_REGISTER = URL_BASE + "user/register";

    public static String URL_GET_VERIFICATION_CODE = URL_BASE + "user/getVerificationCode";

    /**
     * 获得验证码成功
     */
    public static int CODE_REGISTER_VERIFICATION_CODE_SUCCESS = 1;

    public static int CODE_REGISTER_VERIFICATION_CODE_FAIL = 0;


}
