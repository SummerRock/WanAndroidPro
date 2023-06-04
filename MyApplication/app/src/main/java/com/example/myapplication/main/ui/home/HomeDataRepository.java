package com.example.myapplication.main.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.base.net.RetrofitManager;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

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
            public void onResponse(Call<NetworkModel<HomeModelVo>> call, Response<NetworkModel<HomeModelVo>> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NetworkModel<HomeModelVo>> call, Throwable t) {

            }
        });
        return liveData;
    }
}

