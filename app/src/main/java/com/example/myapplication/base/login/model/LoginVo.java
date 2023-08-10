package com.example.myapplication.base.login.model;

import com.google.gson.annotations.SerializedName;

public class LoginVo {

    @SerializedName("id")
    private long id;
    @SerializedName("username")
    private String username;
    @SerializedName("token")
    private String token;
    @SerializedName("icon")
    private String icon;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("signature")
    private String signature;
    @SerializedName("sex")
    private String sex;
    @SerializedName("birthday")
    private String birthday;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getIcon() {
        return icon;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSignature() {
        return signature;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }
}
