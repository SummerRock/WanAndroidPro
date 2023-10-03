package com.example.myapplication.main.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.base.net.RetrofitManager;
import com.example.myapplication.main.ui.home.model.HomeBannerVo;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDataRepository {

    private final HomeService homeService;

    private HomeDataRepository() {
        // 私有的构造方法，防止外部实例化
        homeService = RetrofitManager.getInstance().getRetrofit().create(HomeService.class);
    }

    private static final class InstanceHolder {
        static final HomeDataRepository instance = new HomeDataRepository();
    }

    public static HomeDataRepository getInstance() {
        return InstanceHolder.instance;
    }

    public MutableLiveData<NetworkModel<HomeModelVo>> queryHomeData(Integer pageIndex) {
        MutableLiveData<NetworkModel<HomeModelVo>> liveData = new MutableLiveData<>();
        liveData.setValue(NetworkModel.loading());
        homeService.getHomeMainData(pageIndex).enqueue(new Callback<NetworkModel<HomeModelVo>>() {
            @Override
            public void onResponse(@NonNull Call<NetworkModel<HomeModelVo>> call, @NonNull Response<NetworkModel<HomeModelVo>> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    liveData.setValue(NetworkModel.failed(response.message(), response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<NetworkModel<HomeModelVo>> call, @NonNull Throwable t) {
                liveData.setValue(NetworkModel.failed(t.getMessage(), -1));
            }
        });
        return liveData;
    }

    public MutableLiveData<NetworkModel<List<HomeBannerVo>>> queryBannerData(int index) {
        MutableLiveData<NetworkModel<List<HomeBannerVo>>> liveData = new MutableLiveData<>();
        liveData.setValue(NetworkModel.loading());
        homeService.getHomeBannerData().enqueue(new Callback<NetworkModel<List<HomeBannerVo>>>() {
            @Override
            public void onResponse(@NonNull Call<NetworkModel<List<HomeBannerVo>>> call,
                                   Response<NetworkModel<List<HomeBannerVo>>> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    liveData.setValue(NetworkModel.failed(response.message(), response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<NetworkModel<List<HomeBannerVo>>> call, @NonNull Throwable t) {
                liveData.setValue(NetworkModel.failed(t.getMessage(), -1));
            }
        });
        return liveData;
    }
}

