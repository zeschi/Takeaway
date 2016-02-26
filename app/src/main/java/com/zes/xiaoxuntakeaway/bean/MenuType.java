package com.zes.xiaoxuntakeaway.bean;

/**
 * Created by zes on 16-2-17.
 */
public class MenuType {


    /**
     * menu_type_id : 0
     * merchant_id : 1
     * menu_type_name : 热销菜品
     */

    private String menu_type_id;
    private String merchant_id;
    private String menu_type_name;

    public void setMenu_type_id(String menu_type_id) {
        this.menu_type_id = menu_type_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public void setMenu_type_name(String menu_type_name) {
        this.menu_type_name = menu_type_name;
    }

    public String getMenu_type_id() {
        return menu_type_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public String getMenu_type_name() {
        return menu_type_name;
    }

    @Override
    public String toString() {
        return "MenuType{" +
                "menu_type_id='" + menu_type_id + '\'' +
                ", merchant_id='" + merchant_id + '\'' +
                ", menu_type_name='" + menu_type_name + '\'' +
                '}';
    }
}
