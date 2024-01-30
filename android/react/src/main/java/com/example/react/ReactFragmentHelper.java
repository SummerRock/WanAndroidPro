package com.example.react;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.facebook.react.ReactFragment;

public class ReactFragmentHelper {

    public static Fragment generateReactFragment(String componentName, @Nullable Bundle launchOptions) {
        return new ReactFragment.Builder()
                .setComponentName(componentName)
                .setLaunchOptions(launchOptions)
                .build();
    }
}
