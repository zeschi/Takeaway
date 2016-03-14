package com.zes.xiaoxuntakeaway.bean;

/**
 * Created by zes on 16-3-1.
 */
public class Order {

    /**
     * order_id : 12
     * merchant_id : 1
     * menu_list : [{&quot;menu_good_like&quot;:&quot;2&quot;,&quot;menu_id&quot;:&quot;5&quot;,&quot;menu_mouth_sale&quot;:&quot;14&quot;,&quot;menu_name&quot;:&quot;叉烧饭&quot;,&quot;menu_portrait&quot;:&quot;http://img.waimai.bdimg.com/pb/7a64eea07cfa5db764422ce5ab41228b33
     * user_id : 1
     * order_statues : 0
     */

    private String order_id;
    private String merchant_id;
    private String menu_list;
    private String user_id;
    private String order_statues;

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public void setMenu_list(String menu_list) {
        this.menu_list = menu_list;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setOrder_statues(String order_statues) {
        this.order_statues = order_statues;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public String getMenu_list() {
        return menu_list;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getOrder_statues() {
        return order_statues;
    }
    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", merchant_id='" + merchant_id + '\'' +
                ", menu_list='" + menu_list + '\'' +
                ", user_id='" + user_id + '\'' +
                ", order_statues='" + order_statues + '\'' +
                '}';
    }
}
