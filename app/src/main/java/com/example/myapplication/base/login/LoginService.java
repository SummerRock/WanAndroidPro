package com.example.myapplication.base.login;

import com.example.myapplication.base.favorite.CollectInnerVo;
import com.example.myapplication.base.model.NetworkModel;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginService {

    @POST("lg/collect/{articleId}/json")
    Call<NetworkModel<CollectInnerVo>> postCollectInnerArticle(@Path("articleId") int articleId);
}
