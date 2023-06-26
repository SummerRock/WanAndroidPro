package com.common.mainModule;

import android.text.TextUtils;

import androidx.annotation.NonNull;

public class TextTools {

    public static String getStringWithDefaultValue(String value, @NonNull String defaultValue) {
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }
}
