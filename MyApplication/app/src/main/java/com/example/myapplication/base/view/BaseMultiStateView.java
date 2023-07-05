package com.example.myapplication.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

/**
 * 用于显示界面的网络加载、网络错误信息提示、加载后数据为空提示等状态。
 */
public class BaseMultiStateView extends ConstraintLayout {

    private ProgressBar loadingProgressBar;
    private TextView networkFailedReloadButton;
    private TextView systemErrorReloadButton;
    private ViewGroup networkFailedContent;
    private ViewGroup systemErrorContent;
    private ViewGroup emptyTextViewGroup;

    public BaseMultiStateView(Context context) {
        this(context, null);
    }

    public BaseMultiStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseMultiStateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViewsById();
        try {
            TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.BaseMultiStateView);
            int networkFailImageRes = arr.getResourceId(R.styleable.BaseMultiStateView_common_network_top_image, R.drawable.ic_network_error);
            ImageView networkTopImage = networkFailedContent.findViewById(R.id.base_empty_view_network_failed_top_image);
            if (networkTopImage != null) {
                if (networkFailImageRes != -1) {
                    networkTopImage.setImageResource(networkFailImageRes);
                } else {
                    networkTopImage.setVisibility(GONE);
                }
            }
            int systemFailImageRes = arr.getResourceId(R.styleable.BaseMultiStateView_common_system_top_image, R.drawable.ic_system_fail);
            ImageView systemTopImage = systemErrorContent.findViewById(R.id.base_empty_view_system_error_top_image);
            if (systemTopImage != null) {
                if (systemFailImageRes != -1) {
                    systemTopImage.setImageResource(systemFailImageRes);
                } else {
                    systemTopImage.setVisibility(GONE);
                }
            }
            arr.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViewsById() {
        LayoutInflater.from(getContext()).inflate(getLayoutRes(), this, true);
        loadingProgressBar = findViewById(R.id.base_empty_view_loading_progress);
        emptyTextViewGroup = findViewById(R.id.base_empty_tv_group);

        networkFailedContent = findViewById(R.id.base_empty_view_network_failed_content);
        systemErrorContent = findViewById(R.id.base_empty_view_system_error_content);

        networkFailedReloadButton = findViewById(R.id.base_empty_view_network_failed_reload_btn);
        systemErrorReloadButton = findViewById(R.id.base_empty_view_system_error_reload_btn);
    }

    @LayoutRes
    protected int getLayoutRes() {
        return R.layout.network_status_view;
    }

    public ProgressBar getLoadingProgressBar() {
        return loadingProgressBar;
    }

    public TextView getNetworkFailedReloadButton() {
        return networkFailedReloadButton;
    }

    public TextView getSystemErrorReloadButton() {
        return systemErrorReloadButton;
    }

    public ViewGroup getNetworkFailedContent() {
        return networkFailedContent;
    }

    public ViewGroup getSystemErrorContent() {
        return systemErrorContent;
    }

    public ViewGroup getEmptyTextViewGroup() {
        return emptyTextViewGroup;
    }

    /**
     * 用于显示emptyView的不同状态
     */
    public void show(@BaseMultiStateConstant int state) {
        setVisibility(VISIBLE);
        switch (state) {
            case BaseMultiStateConstant.Loading:
                loadingProgressBar.setVisibility(VISIBLE);
                networkFailedContent.setVisibility(GONE);
                systemErrorContent.setVisibility(GONE);
                emptyTextViewGroup.setVisibility(GONE);
                break;
            case BaseMultiStateConstant.NetworkFail:
                loadingProgressBar.setVisibility(GONE);
                networkFailedContent.setVisibility(VISIBLE);
                systemErrorContent.setVisibility(GONE);
                emptyTextViewGroup.setVisibility(GONE);
                break;
            case BaseMultiStateConstant.SystemError:
                loadingProgressBar.setVisibility(GONE);
                networkFailedContent.setVisibility(GONE);
                systemErrorContent.setVisibility(VISIBLE);
                emptyTextViewGroup.setVisibility(GONE);
                break;
            case BaseMultiStateConstant.ShowEmptyText:
                loadingProgressBar.setVisibility(GONE);
                networkFailedContent.setVisibility(GONE);
                systemErrorContent.setVisibility(GONE);
                emptyTextViewGroup.setVisibility(VISIBLE);
                break;
            default:
                break;
        }
    }

    /**
     * 显示emptyView，不建议直接使用，建议调用带参数的show()方法，方便控制所有子View的显示/隐藏
     */
    public void show() {
        setVisibility(VISIBLE);
    }

    /**
     * 隐藏emptyView
     */
    public void hide() {
        setVisibility(GONE);
    }

    public boolean isShowing() {
        return getVisibility() == VISIBLE;
    }

    public boolean isLoading() {
        return loadingProgressBar.getVisibility() == VISIBLE;
    }

    public void setReloadButtonClickListener(@Nullable OnClickListener onClickListener) {
        networkFailedReloadButton.setOnClickListener(onClickListener);
        systemErrorReloadButton.setOnClickListener(onClickListener);
    }
}
