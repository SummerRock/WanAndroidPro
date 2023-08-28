package com.common.mainModule;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ToastUtils {

    public static void showShortToast(@Nullable Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(@Nullable Context context, String message) {
        showToast(context, message, Toast.LENGTH_LONG);
    }

    private static void showToast(@Nullable Context context, String message, int duration) {
        if (context == null) {
            return;
        }
        Toast.makeText(context, message, duration).show();
    }
}

