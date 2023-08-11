package com.example.myapplication.main.ui.mine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.base.login.LoginManager;

public class MineViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MineViewModel() {
        mText = new MutableLiveData<>();
        if (LoginManager.Companion.getInstance().isLoggedIn()) {
            mText.setValue(LoginManager.Companion.getInstance().getLoginInfo().getUsername());
        } else {
            mText.setValue("未登录");
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}