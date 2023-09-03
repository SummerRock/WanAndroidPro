package com.example.myapplication;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.mainModule.LogUtils;
import com.common.storage.MMKVHelper;
import com.example.monitor.AppLifecycleListener;
import com.example.monitor.FPSMonitor;
import com.example.myapplication.base.login.LoginManager;
import com.example.myapplication.main.MainActivityV2;

public class BaseApplication extends Application {

    private static BaseApplication instance;
    private AppLifecycleListener appLifecycleListener;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MMKVHelper.INSTANCE.initialize(this);
        initMonitor();
        if (BuildConfig.DEBUG) {
            ARouter.openLog(); // 开启日志
            ARouter.openDebug(); // 开启调试模式，如果在 InstantRun 模式下运行，必须开启调试模式！
        }
        ARouter.init(this); // 初始化ARouter
        addLifeCycleMonitor();
        setupCrashHandler();
        LoginManager.Companion.getInstance().init();
    }

    /**
     * 在 onCreate() 方法之前调用
     * @param base The new base context for this wrapper.
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    private void initMonitor() {
        FPSMonitor fpsMonitor = new FPSMonitor();
        fpsMonitor.setFPSListener(new FPSMonitor.FPSListener() {
            @Override
            public void onFPSUpdated(int fps) {
                LogUtils.i("FPS: " + fps);
            }
        });
//        fpsMonitor.start();
    }

    private void addLifeCycleMonitor() {
        appLifecycleListener = new AppLifecycleListener();
        registerActivityLifecycleCallbacks(appLifecycleListener);
    }

    public boolean isAppInForeground() {
        return appLifecycleListener.isAppInForeground();
    }

    private void setupCrashHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                // 在这里执行你的自定义操作，例如记录崩溃信息或上传错误报告
                handleCrash(throwable);
            }
        });
    }

    private void handleCrash(Throwable throwable) {
        // 在这里可以执行自定义操作，例如记录崩溃信息、上传错误报告等
        // 你可以使用日志记录、崩溃报告工具等方式来处理
        LogUtils.e("App crashed: " + throwable.getMessage());

        // 例如，你可以在这里上传错误报告到服务器
        // uploadErrorReportToServer(throwable);

        // 最后，让应用程序崩溃
        System.exit(1);
    }

    private void restartToFirstActivity() {
        Intent intent = new Intent(this, MainActivityV2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, pendingIntent);
        System.exit(2);
    }
}
