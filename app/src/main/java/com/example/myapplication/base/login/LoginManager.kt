package com.example.myapplication.base.login

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

    companion object {
        // 通过 lazy 属性委托确保线程安全的延迟初始化
        val instance: LoginManager by lazy { LoginManager() }
    }
}