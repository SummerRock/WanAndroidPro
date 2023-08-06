package com.example.myapplication.base.login

import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.common.storage.MMKVHelper
import com.example.myapplication.R
import com.example.myapplication.base.login.model.LoginVo
import com.example.myapplication.base.model.NetworkModel
import com.example.myapplication.base.net.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginManager private constructor() {

    init {
        println("Singleton instance initialized.")
    }

    fun doSomething() {
        println("Singleton is doing something.")
    }

    fun showLoginDialog(context: Context) {
        // 获取LayoutInflater实例
        val inflater = LayoutInflater.from(context)
        val dialogView: View = inflater.inflate(R.layout.dialog_custom_login, null)

        // 获取对话框布局中的EditText组件
        val usernameEditText = dialogView.findViewById<EditText>(R.id.editTextUsername)
        val passwordEditText = dialogView.findViewById<EditText>(R.id.editTextPassword)

        // 创建AlertDialog.Builder
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        builder.setTitle(R.string.str_login_title)
        builder.setPositiveButton(R.string.str_login_confirm,
            DialogInterface.OnClickListener { dialog, which ->
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                RetrofitManager.getInstance().retrofit.create(LoginService::class.java)
                    .login(username, password)
                    .enqueue(object : Callback<NetworkModel<LoginVo>> {
                        override fun onResponse(
                            call: Call<NetworkModel<LoginVo>>,
                            response: Response<NetworkModel<LoginVo>>
                        ) {
                            if (response.isSuccessful) {
                                val responseBody = response.body()
                                // 处理响应数据
                            } else {
                                // 处理错误情况
                            }
                        }

                        override fun onFailure(call: Call<NetworkModel<LoginVo>>, t: Throwable) {
                            // 处理网络请求失败情况
                        }
                    })
                // 在这里执行登录逻辑
                // 可以验证用户名密码，向服务器发送请求等
            })
        builder.setNegativeButton(R.string.str_login_cancel,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                // 可以在这里处理取消操作
            })

        // 创建并显示对话框
        val dialog: AlertDialog = builder.create()
        dialog.show()
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
        return !TextUtils.isEmpty(MMKVHelper.getDefaultMMKV().decodeString(KEY_USERNAME, ""))
    }

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_LOGGED_IN = "logged_in"

        // 通过 lazy 属性委托确保线程安全的延迟初始化
        val instance: LoginManager by lazy { LoginManager() }
    }
}