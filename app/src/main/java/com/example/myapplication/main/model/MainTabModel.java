package com.example.myapplication.main.model;

import androidx.annotation.DrawableRes;

public class MainTabModel {

    public MainTabModel(int type) {
        this.type = type;
    }

    private int type;
    private String name;
    private @DrawableRes int icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
