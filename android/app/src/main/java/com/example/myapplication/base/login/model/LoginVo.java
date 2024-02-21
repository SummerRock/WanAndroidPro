package com.example.myapplication.base.login.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginVo {

    @SerializedName("admin")
    private boolean admin;
    @SerializedName("coinCount")
    private int coinCount;
    @SerializedName("collectIds")
    private List<Integer> collectIds;

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
    @SerializedName("publicName")
    private String publicName;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("type")
    private int type;

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

    public boolean isAdmin() {
        return admin;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public String getPassword() {
        return password;
    }

    public String getPublicName() {
        return publicName;
    }

    public String getNickname() {
        return nickname;
    }

    public int getType() {
        return type;
    }
}
