package com.example.myapplication.base.view;


import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatToggleButton;

public class CustomCompoundButton extends AppCompatToggleButton {
    public CustomCompoundButton(Context context) {
        super(context);
    }

    public CustomCompoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCompoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void toggle() {
        // super.toggle(); 禁用toggle方法，防止点击后立即
    }
}

