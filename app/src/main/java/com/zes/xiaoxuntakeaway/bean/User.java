package com.zes.xiaoxuntakeaway.bean;

import java.io.Serializable;

/**
 * Created by zes on 16-2-16.
 */
public class User implements Serializable {


    /**
     * user_id : 19
     * user_name : null
     * user_password : aa
     * user_address : null
     * user_portrait : null
     * user_account : 233
     */

    private String user_id;
    private String user_name;
    private String user_password;
    private String user_address;
    private String user_portrait;
    private String user_account;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_portrait() {
        return user_portrait;
    }

    public void setUser_portrait(String user_portrait) {
        this.user_portrait = user_portrait;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_address='" + user_address + '\'' +
                ", user_portrait='" + user_portrait + '\'' +
                ", user_account='" + user_account + '\'' +
                '}';
    }
}
