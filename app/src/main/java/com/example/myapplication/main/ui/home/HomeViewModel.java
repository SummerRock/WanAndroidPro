package com.example.myapplication.main.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.main.ui.home.model.HomeBannerVo;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<Integer> mutablePageIndexData = new MutableLiveData<>();
    private final LiveData<NetworkModel<HomeModelVo>> homeListLiveData;

    private final MutableLiveData<Integer> mutableBannerData = new MutableLiveData<>();

    private final LiveData<NetworkModel<List<HomeBannerVo>>> homeBannerLiveData;

    public HomeViewModel() {
        homeListLiveData = Transformations.switchMap(mutablePageIndexData, input -> HomeDataRepository.getInstance().queryHomeData(mutablePageIndexData.getValue()));
        homeBannerLiveData = Transformations.switchMap(mutableBannerData, input -> HomeDataRepository.getInstance().queryBannerData(input));
    }

    @NonNull
    public MutableLiveData<Integer> getMutablePageIndexData() {
        return mutablePageIndexData;
    }

    public void triggerHomeData(int pageIndex) {
        mutablePageIndexData.setValue(pageIndex);
    }

    public LiveData<NetworkModel<HomeModelVo>> getHomeListLiveData() {
        return homeListLiveData;
    }

    public LiveData<NetworkModel<List<HomeBannerVo>>> getHomeBannerLiveData() {
        return homeBannerLiveData;
    }
}