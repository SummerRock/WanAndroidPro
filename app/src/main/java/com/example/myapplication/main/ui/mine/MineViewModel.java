package com.example.myapplication.main.ui.mine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MineViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("张三");
    }

    public LiveData<String> getText() {
        return mText;
    }
}