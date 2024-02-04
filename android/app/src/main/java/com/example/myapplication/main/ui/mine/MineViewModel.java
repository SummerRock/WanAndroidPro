package com.example.myapplication.main.ui.mine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.base.login.LoginManager;
import com.example.myapplication.base.login.model.LoginVo;
import com.example.myapplication.base.model.NetworkModel;

public class MineViewModel extends ViewModel {

    private final MutableLiveData<LoginVo> loginInfoLiveData; // 监听登录信息变化
    private final MutableLiveData<String> messageCountTrigger;

    private final LiveData<NetworkModel<Integer>> messageCountLiveData;

    public MineViewModel() {
        loginInfoLiveData = new MutableLiveData<>();
        messageCountTrigger = new MutableLiveData<>();
        loginInfoLiveData.setValue(LoginManager.Companion.getInstance().getLoginInfo());
        messageCountLiveData = Transformations.switchMap(messageCountTrigger, s -> MineDataRepository.getInstance().queryUnreadMessageCount(s));
    }

    public MutableLiveData<LoginVo> getLoginInfo() {
        return loginInfoLiveData;
    }

    public void triggerMessageCount() {
        messageCountTrigger.setValue("");
    }

    public LiveData<NetworkModel<Integer>> getMessageCountLiveData() {
        return messageCountLiveData;
    }
}