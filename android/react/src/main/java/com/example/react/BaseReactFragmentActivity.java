package com.example.react;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.common.router.RouterConstants;
import com.facebook.react.ReactFragment;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;

@Route(path = RouterConstants.REACT_FRAGMENT_ACTIVITY)
public class BaseReactFragmentActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
    // 必须要实现DefaultHardwareBackBtnHandler

    @Autowired
    String componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_react_fragment);

        // 注入属性
        ARouter.getInstance().inject(this);

        Fragment reactNativeFragment = new ReactFragment.Builder()
                .setComponentName(componentName)
                .setLaunchOptions(getLaunchOptions("test message"))
                .setFabricEnabled(true)
                .build();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.reactNativeFragment, reactNativeFragment)
                .commit();
    }

    private Bundle getLaunchOptions(String message) {
        Bundle initialProperties = new Bundle();
        initialProperties.putString("message", message);
        return initialProperties;
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
}