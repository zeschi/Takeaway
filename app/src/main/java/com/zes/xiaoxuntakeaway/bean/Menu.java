package com.zes.xiaoxuntakeaway.bean;

import java.io.Serializable;

/**
 * Created by zes on 16-2-17.
 */
public class Menu implements Serializable{

    /**
     * menu_id : 0
     * menu_name : 卤鸡腿饭
     * merchant_id : 1
     * menu_mouth_sale : 25
     * menu_sale_price : 12
     * menu_good_like : 3
     * menu_portrait : null
     * menu_type_id : 0
     */

    private String menu_id;
    private String menu_name;
    private String merchant_id;
    private String menu_mouth_sale;
    private String menu_sale_price;
    private String menu_good_like;
    private Object menu_portrait;
    private String menu_type_id;

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public void setMenu_mouth_sale(String menu_mouth_sale) {
        this.menu_mouth_sale = menu_mouth_sale;
    }

    public void setMenu_sale_price(String menu_sale_price) {
        this.menu_sale_price = menu_sale_price;
    }

    public void setMenu_good_like(String menu_good_like) {
        this.menu_good_like = menu_good_like;
    }

    public void setMenu_portrait(Object menu_portrait) {
        this.menu_portrait = menu_portrait;
    }

    public void setMenu_type_id(String menu_type_id) {
        this.menu_type_id = menu_type_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public String getMenu_mouth_sale() {
        return menu_mouth_sale;
    }

    public String getMenu_sale_price() {
        return menu_sale_price;
    }

    public String getMenu_good_like() {
        return menu_good_like;
    }

    public Object getMenu_portrait() {
        return menu_portrait;
    }

    public String getMenu_type_id() {
        return menu_type_id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id='" + menu_id + '\'' +
                ", menu_name='" + menu_name + '\'' +
                ", merchant_id='" + merchant_id + '\'' +
                ", menu_mouth_sale='" + menu_mouth_sale + '\'' +
                ", menu_sale_price='" + menu_sale_price + '\'' +
                ", menu_good_like='" + menu_good_like + '\'' +
                ", menu_portrait=" + menu_portrait +
                ", menu_type_id='" + menu_type_id + '\'' +
                '}';
    }
}
