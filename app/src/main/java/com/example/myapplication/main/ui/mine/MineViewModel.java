package com.example.myapplication.main.ui.mine;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.base.login.LoginManager;
import com.example.myapplication.base.login.model.LoginVo;

public class MineViewModel extends ViewModel {

    private final MutableLiveData<LoginVo> loginInfoLiveData; // 监听登录信息变化

    public MineViewModel() {
        loginInfoLiveData = new MutableLiveData<>();
        loginInfoLiveData.setValue(LoginManager.Companion.getInstance().getLoginInfo());
    }

    public MutableLiveData<LoginVo> getLoginInfo() {
        return loginInfoLiveData;
    }
}