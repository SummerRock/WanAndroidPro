package com.common.mainModule;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

public class RouterUtils {
    public static void jumpScheme(String scheme, Context context) {
        if (!TextUtils.isEmpty(scheme) && context != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(scheme));
            ContextCompat.startActivity(context, intent, null);
        }
    }
}
