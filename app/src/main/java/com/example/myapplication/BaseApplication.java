package com.example.myapplication;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.mainModule.LogUtils;
import com.common.storage.MMKVHelper;
import com.example.monitor.AppLifecycleListener;
import com.example.monitor.FPSMonitor;

public class BaseApplication extends Application implements Thread.UncaughtExceptionHandler {

    private AppLifecycleListener appLifecycleListener;

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);
        MMKVHelper.INSTANCE.initialize(this);
        initMonitor();
        if (BuildConfig.DEBUG) {
            ARouter.openLog(); // 开启日志
            ARouter.openDebug(); // 开启调试模式，如果在 InstantRun 模式下运行，必须开启调试模式！
        }
        ARouter.init(this); // 初始化ARouter
        addLifeCycleMonitor();
    }

    private void initMonitor() {
        FPSMonitor fpsMonitor = new FPSMonitor();
        fpsMonitor.setFPSListener(new FPSMonitor.FPSListener() {
            @Override
            public void onFPSUpdated(int fps) {
                LogUtils.i("FPS: " + fps);
            }
        });
        fpsMonitor.start();
    }

    private void addLifeCycleMonitor() {
        appLifecycleListener = new AppLifecycleListener();
        registerActivityLifecycleCallbacks(appLifecycleListener);
    }

    public boolean isAppInForeground() {
        return appLifecycleListener.isAppInForeground();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
//        Intent intent = new Intent(this, MainActivityV2.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, pendingIntent);
//        System.exit(2);
    }
}
