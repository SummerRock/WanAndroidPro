package com.example.monitor;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;

import com.common.mainModule.LogUtils;

public class MemoryMonitor {

    public static void printMemoryInfo(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        // 获取应用进程的内存信息
        int pid = android.os.Process.myPid();
        Debug.MemoryInfo memoryInfo = activityManager.getProcessMemoryInfo(new int[]{pid})[0];

        // 输出内存信息
        long totalMemory = memoryInfo.getTotalPss() * 1024L; // 将KB转换为字节
        long dalvikMemory = memoryInfo.dalvikPrivateDirty * 1024L;
        long nativeMemory = memoryInfo.nativePrivateDirty * 1024L;
        long otherMemory = memoryInfo.otherPrivateDirty * 1024L;

        LogUtils.i("Total Memory: " + totalMemory + " bytes");
        LogUtils.i("Dalvik Memory: " + dalvikMemory + " bytes");
        LogUtils.i("Native Memory: " + nativeMemory + " bytes");
        LogUtils.i("Other Memory: " + otherMemory + " bytes");
//        System.out.println("Total Memory: " + totalMemory + " bytes");
//        System.out.println("Dalvik Memory: " + dalvikMemory + " bytes");
//        System.out.println("Native Memory: " + nativeMemory + " bytes");
//        System.out.println("Other Memory: " + otherMemory + " bytes");
    }
}

