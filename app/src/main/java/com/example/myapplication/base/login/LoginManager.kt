package com.example.myapplication.base.login

import com.common.storage.MMKVHelper

class LoginManager private constructor() {

    init {
        println("Singleton instance initialized.")
    }

    fun doSomething() {
        println("Singleton is doing something.")
    }

    fun isLogin(): Boolean {
        return false
    }

    // 保存用户名
    fun saveUsername(username: String) {
        MMKVHelper.getDefaultMMKV().encode(KEY_USERNAME, username)
    }

    // 获取用户名
    fun getUsername(): String {
        return MMKVHelper.getDefaultMMKV().decodeString(KEY_USERNAME, "") ?: ""
    }

    // 保存登录状态
    fun setLoggedIn(loggedIn: Boolean) {
        MMKVHelper.getDefaultMMKV().encode(KEY_LOGGED_IN, loggedIn)
    }

    // 获取登录状态
    fun isLoggedIn(): Boolean {
        return MMKVHelper.getDefaultMMKV().decodeBool(KEY_LOGGED_IN, false)
    }

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_LOGGED_IN = "logged_in"
        // 通过 lazy 属性委托确保线程安全的延迟初始化
        val instance: LoginManager by lazy { LoginManager() }
    }
}