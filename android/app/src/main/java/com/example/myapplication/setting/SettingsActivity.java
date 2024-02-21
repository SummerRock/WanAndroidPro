package com.example.myapplication.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.common.mainModule.ToastUtils;
import com.common.router.RouterConstants;
import com.example.monitor.FloatWindowManager;
import com.example.myapplication.BaseApplication;
import com.example.myapplication.R;

@Route(path = RouterConstants.SETTING_ACTIVITY)
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements
            SharedPreferences.OnSharedPreferenceChangeListener {

        private static final int FLOAT_WINDOW_PERMISSION_REQUEST_CODE = 123;
        public static final String FLOAT_WINDOW_KEY = "global_float_window_sync";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        private void checkPermission() {
            if (Settings.canDrawOverlays(requireContext())) {

            } else {
                // 请求悬浮窗权限
                requestOverlayPermission();
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            // 注册 SharedPreferences 监听器
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            // 注销 SharedPreferences 监听器
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            // 当设置项的值发生变化时，会调用此方法
            if (key.equals(FLOAT_WINDOW_KEY)) {
                // 处理相应的设置项变化
                boolean enableFeature = sharedPreferences.getBoolean(key, false);
                // 进行相应的操作
                if (enableFeature) {
                    // 如果用户开启了设置项，请求悬浮窗权限
                    if (requestOverlayPermission()) {
                        // 请求成功，执行开启成功操作
                        // 可以在这里启用你的悬浮窗功能
                        FloatWindowManager.getInstance().showOverlayView(BaseApplication.getInstance());
                    } else {
                        // 请求失败，执行开启失败操作
                    }
                } else {
                    // 用户关闭了设置项，可以在这里执行关闭相关功能的操作
                    FloatWindowManager.getInstance().hideOverlayView();
                }
            } else if (key.equals("pref_key_username")) {
                // 处理相应的设置项变化
                String username = sharedPreferences.getString(key, "");
                // 进行相应的操作
            }
        }

        // 请求悬浮窗权限
        private boolean requestOverlayPermission() {
            if (Settings.canDrawOverlays(requireContext())) {
                // 权限已被授予
                return true;
            } else {
                // 请求悬浮窗权限
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + requireContext().getPackageName()));
                startActivityForResult(intent, FLOAT_WINDOW_PERMISSION_REQUEST_CODE);
                return false;
            }
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == FLOAT_WINDOW_PERMISSION_REQUEST_CODE) {
                if (Settings.canDrawOverlays(requireContext())) {
                    // 用户已授予悬浮窗权限，可以执行相应操作
                    // 启用你的悬浮窗功能
                    FloatWindowManager.getInstance().showOverlayView(BaseApplication.getInstance());
                } else {
                    // 用户拒绝了悬浮窗权限，执行失败操作
                    ToastUtils.showShortToast(requireContext(), "请求失败，请重新尝试");
                    FloatWindowManager.getInstance().hideOverlayView();
                }
            }
        }
    }
}