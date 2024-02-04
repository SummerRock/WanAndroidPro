package com.example.myapplication.main.ui.mine;

import com.example.myapplication.base.model.NetworkModel;


import retrofit2.Call;
import retrofit2.http.GET;

public interface MineService {

    @GET("message/lg/count_unread/json")
    Call<NetworkModel<Integer>> getUnreadMessageCount();
}
