package com.example.myapplication.base.favorite;

import androidx.annotation.NonNull;

import com.common.mainModule.ToastUtils;
import com.example.myapplication.BaseApplication;
import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.base.net.RetrofitManager;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectDataRepository {
    private CollectDataRepository() {
        // 私有化构造方法，防止外部实例化
    }

    private static class SingletonHolder {
        // 静态内部类，只有在第一次使用时才会加载
        private static final CollectDataRepository INSTANCE = new CollectDataRepository();
    }

    public static CollectDataRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void queryCollectArticle(int articleId) {
        RetrofitManager.getInstance().getRetrofit().create(FavoriteService.class).postCollectInnerArticle(articleId).enqueue(new Callback<NetworkModel<CollectInnerVo>>() {
            @Override
            public void onResponse(@NonNull Call<NetworkModel<CollectInnerVo>> call,
                                   @NonNull Response<NetworkModel<CollectInnerVo>> response) {
                if (response.isSuccessful()) {
                    EventBus.getDefault().post(new CollectSuccessEvent(articleId));
                } else {
                    ToastUtils.showShortToast(BaseApplication.getInstance(), "收藏失败");
                }
            }

            @Override
            public void onFailure(@NonNull Call<NetworkModel<CollectInnerVo>> call, @NonNull Throwable t) {

            }
        });
    }

    public void queryUnCollectArticle(int id) {
        RetrofitManager.getInstance().getRetrofit().create(FavoriteService.class).postUnCollectArticle(id).enqueue(new Callback<NetworkModel<CollectInnerVo>>() {
            @Override
            public void onResponse(@NonNull Call<NetworkModel<CollectInnerVo>> call,
                                   @NonNull Response<NetworkModel<CollectInnerVo>> response) {
                if (response.isSuccessful()) {
                    EventBus.getDefault().post(new UnCollectEvent(id));
                }
            }

            @Override
            public void onFailure(@NonNull Call<NetworkModel<CollectInnerVo>> call, @NonNull Throwable t) {

            }
        });
    }
}

