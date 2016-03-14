package com.zes.xiaoxuntakeaway.bean;

import java.io.Serializable;

/**
 * Created by zes on 16-3-13.
 */
public class Address implements Serializable{

    private String userRealName;
    private String userPhone;
    private String userId;
    private String doorPlate;
    private String receiptAddress;

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoorPlate() {
        return doorPlate;
    }

    public void setDoorPlate(String doorPlate) {
        this.doorPlate = doorPlate;
    }

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    @Override
    public String toString() {
        return "Address{" +
                "userRealName='" + userRealName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userId='" + userId + '\'' +
                ", doorPlate='" + doorPlate + '\'' +
                ", receiptAddress='" + receiptAddress + '\'' +
                '}';
    }
}
