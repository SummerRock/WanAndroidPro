package com.example.myapplication.main.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    private final LiveData<NetworkModel<HomeModelVo>> liveData;

    public HomeViewModel() {
        liveData = Transformations.switchMap(mutableLiveData, input -> HomeDataRepository.getInstance().queryHomeData(mutableLiveData.getValue()));
    }

    @NonNull
    public MutableLiveData<Integer> getMutableLiveData() {
        return mutableLiveData;
    }

    public void triggerHomeData(int pageIndex) {
        mutableLiveData.setValue(pageIndex);
    }

    public LiveData<NetworkModel<HomeModelVo>> getLiveData() {
        return liveData;
    }
}