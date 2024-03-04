package com.example.myapplication;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.MemoryCategory;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.common.mainModule.LogUtils;
import com.common.storage.MMKVHelper;
import com.example.monitor.AppLifecycleListener;
import com.example.myapplication.base.login.LoginManager;
import com.example.myapplication.main.MainActivityV2;
import com.example.react.module.DefaultModulePackage;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.defaults.DefaultReactNativeHost;
import com.facebook.soloader.SoLoader;

import java.util.List;

public class BaseApplication extends Application implements ReactApplication {

    private static BaseApplication instance;
    private AppLifecycleListener appLifecycleListener;

    private final ReactNativeHost mReactNativeHost = new DefaultReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        protected List<ReactPackage> getPackages() {
            List<ReactPackage> packages = new PackageList(this).getPackages();
            // Packages that cannot be autolinked yet can be added manually here
            packages.add(new DefaultModulePackage());
            return packages;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MMKVHelper.INSTANCE.initialize(this);
        if (BuildConfig.DEBUG) {
            ARouter.openLog(); // 开启日志
            ARouter.openDebug(); // 开启调试模式，如果在 InstantRun 模式下运行，必须开启调试模式！
        }
        ARouter.init(this); // 初始化ARouter
        // 初始化 Glide
        Glide.init(this, new GlideBuilder().setDefaultRequestOptions(new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)));
        // 设置全局 Glide 配置（可选）
        Glide.get(this).setMemoryCategory(MemoryCategory.LOW);
        // Glide.get(this).setDiskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        addLifeCycleMonitor();
        setupCrashHandler();
        LoginManager.Companion.getInstance().init();
        SoLoader.init(this, false);
    }

    /**
     * 在 onCreate() 方法之前调用
     *
     * @param base The new base context for this wrapper.
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static BaseApplication getInstance() {
        return instance;
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

    @NonNull
    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
}
