package com.example.monitor;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class FPSOverlayView {
    private WindowManager windowManager;
    private View overlayView;
    private TextView fpsTextView;

    public FPSOverlayView(@NonNull Context context) {
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(context);
        overlayView = inflater.inflate(R.layout.layout_fps_overlay, null);
        overlayView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                setFPS(58);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });

        WindowManager.LayoutParams params;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
        } else {
            params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT
            );
        }

        params.gravity = Gravity.TOP | Gravity.END;
        params.x = 0;
        params.y = 0;

        fpsTextView = overlayView.findViewById(R.id.fpsTextView);

        windowManager.addView(overlayView, params);

        FPSMonitor fpsMonitor = new FPSMonitor();
        fpsMonitor.setFPSListener(this::setFPS);
        fpsMonitor.start();
    }

    public void setFPS(int fps) {
        fpsTextView.setText(String.format("FPS: %d", fps));
    }

    public void hide() {
        if (overlayView != null) {
            windowManager.removeView(overlayView);
            overlayView = null;
        }
    }
}


