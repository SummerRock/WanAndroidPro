package com.example.myapplication.main.ui.home;

import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.main.ui.home.model.HomeBannerVo;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeService {

    @GET("article/list/{pageIndex}/json")
    Call<NetworkModel<HomeModelVo>> getHomeMainData(@Path("pageIndex") int pageIndex);

    @GET("banner/json")
    Call<NetworkModel<List<HomeBannerVo>>> getHomeBannerData();
}
