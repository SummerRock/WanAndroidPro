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

        //当 Java 堆内存不足时，我们需要对应用中的缓存进行一次清理，这样能减少 OOM。
        // 那如何才能知道 Java 堆不足呢？这就需要增加一个检测的机制了，我们可以开启一个独立的子线程，然后每隔一定的频率检测一次
        // 获取当前虚拟机实例的内存使用上限
        long runtimeMem = Runtime.getRuntime().maxMemory();
        //获取当前已经申请的内存
        long totalMem = Runtime.getRuntime().totalMemory();
        LogUtils.i("当前虚拟机实例的内存: " + runtimeMem + " 当前已经申请的内存: " + totalMem);

        MemObserver.getInstance().setState(new MemInfo(totalMemory));
    }
}

