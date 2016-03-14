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

    public static String URL_GET_MERCHANT_LIST = URL_BASE + "merchant/getMerchantList";

    public static String URL_GET_MENU_LIST = URL_BASE + "menu/getMenuByMerchantId";

    public static String URL_GET_MENU_TYPE_LIST = URL_BASE + "menuType/getMenuTypeByMerchantId";

    public static String URL_GET_MERCHANT_INFO = URL_BASE + "merchant/getMerchantById";

    public static String URL_CREATE_ORDER = URL_BASE + "order/createOrder";

    public static String URL_GET_ORDER_BY_USER_ID = URL_BASE + "order/getOrderByUserId";

    public static String URL_SAVE_USER_ADDRESS=URL_BASE+"address/addAddress";

    public static String URL_CAHNGE_USER_ADDRESS=URL_BASE+"address/changeAddress";

    public static String URL_DELETE_USER_ADDRESS=URL_BASE+"address/deleteAddress";
    /**
     * 获得验证码成功
     */
    public static int CODE_REGISTER_VERIFICATION_CODE_SUCCESS = 1;
    /**
     * 　注册成功
     */
    public static int CODE_REGISTER_REGISTER_SUCCESS = 1;
    /**
     * 登录成功
     */
    public static int CODE_LOGIN_LOGIN_SUCCESS = 1;
}
