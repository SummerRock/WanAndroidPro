package com.common.storage

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.util.Preconditions.checkNotNull
import com.tencent.mmkv.MMKV

object MMKVHelper {
    private var defaultMMKV: MMKV? = null

    fun initialize(context: Context) {
        MMKV.initialize(context)
        defaultMMKV = MMKV.defaultMMKV()
    }

    @SuppressLint("RestrictedApi")
    fun getDefaultMMKV(): MMKV {
        checkNotNull(defaultMMKV) { "MMKV is not initialized. Call initialize() first." }
        return defaultMMKV!!
    }

    // 如果需要，你可以创建其他 MMKV 实例来管理不同的 key-value 数据
    // fun getCustomMMKV(mmkvId: String): MMKV {
    //     return MMKV.mmkvWithID(mmkvId, MMKV.MULTI_PROCESS_MODE)
    // }
}
