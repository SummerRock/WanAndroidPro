package com.example.myapplication.main;

import android.os.Bundle;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainV2Binding;

public class MainActivityV2 extends AppCompatActivity {

    private ActivityMainV2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainV2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_mine)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_v2);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    /**
     * 当前活动失去焦点并停止：当用户通过按下 Home 键、最近任务键、屏幕关闭或其他方式离开当前活动，将其放入后台并停止时，onRestart() 方法会在用户再次返回到该活动时被调用。
     * <p>
     * 启动另一个活动后返回：当用户从当前活动启动了另一个活动，并在后来通过返回按钮或者其他方式返回到当前活动时，onRestart() 方法也会被调用。
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }
}