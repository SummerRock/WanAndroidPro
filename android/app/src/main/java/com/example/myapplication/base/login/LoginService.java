package com.example.myapplication.base.login;

import com.example.myapplication.base.login.model.LoginVo;
import com.example.myapplication.base.model.EmptyVo;
import com.example.myapplication.base.model.NetworkModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("user/login")
    Call<NetworkModel<LoginVo>> login(@Field("username") String username, @Field("password") String password);

    @GET("user/logout/json")
    Call<NetworkModel<EmptyVo>> logout();
}
