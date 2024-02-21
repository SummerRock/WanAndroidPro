package com.example.myapplication.main.ui.mine

import androidx.lifecycle.MutableLiveData
import com.common.net.RetrofitManager
import com.example.myapplication.base.model.NetworkModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MineDataRepository private constructor() {
    private val service: MineService =
        RetrofitManager.getInstance().retrofit.create(MineService::class.java)

    private object HOLDER {
        val instance = MineDataRepository()
    }

    fun queryUnreadMessageCount(str: String?): MutableLiveData<NetworkModel<Int?>> {
        val liveData = MutableLiveData<NetworkModel<Int?>>()
        liveData.value = NetworkModel.loading()
        service.unreadMessageCount.enqueue(object : Callback<NetworkModel<Int?>?> {
            override fun onResponse(
                call: Call<NetworkModel<Int?>?>,
                response: Response<NetworkModel<Int?>?>
            ) {
                if (response.isSuccessful) {
                    liveData.setValue(response.body())
                } else {
                    liveData.setValue(NetworkModel.failed(response.message(), response.code()))
                }
            }

            override fun onFailure(call: Call<NetworkModel<Int?>?>, t: Throwable) {
                liveData.value = NetworkModel.failed(t.message, -1)
            }
        })
        return liveData
    }

    companion object {
        @JvmStatic
        val instance: MineDataRepository
            get() = HOLDER.instance
    }
}