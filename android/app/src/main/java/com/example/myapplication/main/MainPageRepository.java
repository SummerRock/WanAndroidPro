package com.example.myapplication.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.main.model.MainTabModel;

import java.util.List;

public class MainPageRepository {
    private static MainPageRepository instance;

    private MainPageRepository() {
        // 私有的构造方法，防止外部实例化
    }

    public static MainPageRepository getInstance() {
        if (instance == null) {
            synchronized (MainPageRepository.class) {
                if (instance == null) {
                    instance = new MainPageRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<NetworkModel<List<MainTabModel>>> queryMainData() {
        MutableLiveData<NetworkModel<List<MainTabModel>>> liveData = new MutableLiveData<>();
        liveData.setValue(null); // TODO
        return liveData;
    }
}

