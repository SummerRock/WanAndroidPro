package com.example.myapplication.base.login;

import com.example.myapplication.base.login.model.LoginVo;

public class LoginEvent {

    public final LoginVo loginVo;

    public LoginEvent(LoginVo loginVo) {
        this.loginVo = loginVo;
    }
}
