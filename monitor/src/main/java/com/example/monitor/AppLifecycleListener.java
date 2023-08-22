package com.example.monitor;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class AppLifecycleListener implements Application.ActivityLifecycleCallbacks {

    private int activityCounter = 0;
    private boolean isAppInForeground = false;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        MemoryMonitor.printMemoryInfo(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityCounter++;
        if (!isAppInForeground) {
            // 应用从后台切换到前台
            isAppInForeground = true;
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityCounter--;
        if (activityCounter == 0) {
            // 应用从前台切换到后台
            isAppInForeground = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        MemoryMonitor.printMemoryInfo(activity);
    }

    public boolean isAppInForeground() {
        return isAppInForeground;
    }
}

