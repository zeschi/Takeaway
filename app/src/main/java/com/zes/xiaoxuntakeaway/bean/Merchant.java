package com.zes.xiaoxuntakeaway.bean;

/**
 * Created by zes on 16-2-16.
 */

public class Merchant {


    /**
     * merchant_id : 1
     * merchant_name : 隆江猪脚饭
     * merchant_start_price : 20.00
     * merchant_delivery_price : 3.00
     * merchant_score : 4.50
     * merchant_month_sale : 777
     * merchant_portrait : http://img.waimai.bdimg.com/pb/cb92a42a545f21e1c0026632482cd4500a@s_0,w_297,h_180,q_100
     * merchant_discount_details : 满20减3,满30减5,满49减9
     * merchant_category_id : 1
     * merchant_address : 长湴中路3号大院长湴综合市场#铺18
     * merchant_average_delivery_time : 40分钟
     * merchant_announcement : 本店欢迎您下单，用餐高峰请提前下单，谢谢！
     * merchant_business_hours : 10:00-21:30
     */

    private String merchant_id;
    private String merchant_name;
    private String merchant_start_price;
    private String merchant_delivery_price;
    private String merchant_score;
    private String merchant_month_sale;
    private String merchant_portrait;
    private String merchant_discount_details;
    private String merchant_category_id;
    private String merchant_address;
    private String merchant_average_delivery_time;
    private String merchant_announcement;
    private String merchant_business_hours;

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public void setMerchant_start_price(String merchant_start_price) {
        this.merchant_start_price = merchant_start_price;
    }

    public void setMerchant_delivery_price(String merchant_delivery_price) {
        this.merchant_delivery_price = merchant_delivery_price;
    }

    public void setMerchant_score(String merchant_score) {
        this.merchant_score = merchant_score;
    }

    public void setMerchant_month_sale(String merchant_month_sale) {
        this.merchant_month_sale = merchant_month_sale;
    }

    public void setMerchant_portrait(String merchant_portrait) {
        this.merchant_portrait = merchant_portrait;
    }

    public void setMerchant_discount_details(String merchant_discount_details) {
        this.merchant_discount_details = merchant_discount_details;
    }

    public void setMerchant_category_id(String merchant_category_id) {
        this.merchant_category_id = merchant_category_id;
    }

    public void setMerchant_address(String merchant_address) {
        this.merchant_address = merchant_address;
    }

    public void setMerchant_average_delivery_time(String merchant_average_delivery_time) {
        this.merchant_average_delivery_time = merchant_average_delivery_time;
    }

    public void setMerchant_announcement(String merchant_announcement) {
        this.merchant_announcement = merchant_announcement;
    }

    public void setMerchant_business_hours(String merchant_business_hours) {
        this.merchant_business_hours = merchant_business_hours;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public String getMerchant_start_price() {
        return merchant_start_price;
    }

    public String getMerchant_delivery_price() {
        return merchant_delivery_price;
    }

    public String getMerchant_score() {
        return merchant_score;
    }

    public String getMerchant_month_sale() {
        return merchant_month_sale;
    }

    public String getMerchant_portrait() {
        return merchant_portrait;
    }

    public String getMerchant_discount_details() {
        return merchant_discount_details;
    }

    public String getMerchant_category_id() {
        return merchant_category_id;
    }

    public String getMerchant_address() {
        return merchant_address;
    }

    public String getMerchant_average_delivery_time() {
        return merchant_average_delivery_time;
    }

    public String getMerchant_announcement() {
        return merchant_announcement;
    }

    public String getMerchant_business_hours() {
        return merchant_business_hours;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchant_id='" + merchant_id + '\'' +
                ", merchant_name='" + merchant_name + '\'' +
                ", merchant_start_price='" + merchant_start_price + '\'' +
                ", merchant_delivery_price='" + merchant_delivery_price + '\'' +
                ", merchant_score='" + merchant_score + '\'' +
                ", merchant_month_sale='" + merchant_month_sale + '\'' +
                ", merchant_portrait='" + merchant_portrait + '\'' +
                ", merchant_discount_details='" + merchant_discount_details + '\'' +
                ", merchant_category_id='" + merchant_category_id + '\'' +
                ", merchant_address='" + merchant_address + '\'' +
                ", merchant_average_delivery_time='" + merchant_average_delivery_time + '\'' +
                ", merchant_announcement='" + merchant_announcement + '\'' +
                ", merchant_business_hours='" + merchant_business_hours + '\'' +
                '}';
    }
}
