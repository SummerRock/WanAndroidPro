package com.example.monitor;

import android.os.Handler;
import android.os.Looper;
import android.view.Choreographer;

public class FPSMonitor implements Choreographer.FrameCallback {
    private static final long FRAME_INTERVAL = 1000; // 更新间隔，以毫秒为单位
    private long lastFrameTime = 0;
    private int framesRendered = 0;

    private Handler handler = new Handler(Looper.getMainLooper());

    private FPSListener fpsListener;

    public interface FPSListener {
        void onFPSUpdated(int fps);
    }

    public void setFPSListener(FPSListener listener) {
        fpsListener = listener;
    }

    public void start() {
        Choreographer.getInstance().postFrameCallback(this);
    }

    public void stop() {
        Choreographer.getInstance().removeFrameCallback(this);
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void doFrame(long frameTimeNanos) {
        if (lastFrameTime == 0) {
            lastFrameTime = frameTimeNanos;
        }

        framesRendered++;

        if (frameTimeNanos - lastFrameTime >= FRAME_INTERVAL * 1000000) {
            int fps = (int) (framesRendered * 1000 / FRAME_INTERVAL);
            if (fpsListener != null) {
                fpsListener.onFPSUpdated(fps);
            }

            framesRendered = 0;
            lastFrameTime = frameTimeNanos;
        }

        Choreographer.getInstance().postFrameCallback(this);
    }
}


