package com.example.myapplication.main.ui.mine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.router.RouterConstants;
import com.example.myapplication.base.fragment.BaseFragment;
import com.example.myapplication.base.login.LoginEvent;
import com.example.myapplication.base.login.LoginManager;
import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.databinding.FragmentMineBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

import io.flutter.embedding.android.FlutterActivity;


public class MineFragment extends BaseFragment<MineViewModel, FragmentMineBinding> {

    @Override
    protected Class<MineViewModel> getViewModelClass() {
        return MineViewModel.class;
    }

    @Override
    protected FragmentMineBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentMineBinding.inflate(inflater, container, false);
    }

    @Override
    protected void performAction() {
        View headerLayout = binding.headerLayout;
        headerLayout.setOnClickListener(view -> {
            if (!LoginManager.Companion.getInstance().isLoggedIn()) {
                LoginManager.Companion.getInstance().showLoginDialog(requireContext());
            } else {
                LoginManager.Companion.getInstance().showLogoutDialog(requireContext());
            }
        });
        final TextView userNameText = binding.profileName;
        final TextView coinCountText = binding.userCoinCount;
        coinCountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FlutterActivity.createDefaultIntent(requireContext()));
            }
        });
        viewModel.getMessageCountLiveData().observe(getViewLifecycleOwner(), new Observer<NetworkModel<Integer>>() {
            @Override
            public void onChanged(NetworkModel<Integer> integerNetworkModel) {
                int count = integerNetworkModel != null && integerNetworkModel.data != null ? integerNetworkModel.data : 0;
                binding.myMessageCountTv.setText(String.format(Locale.getDefault(), "(%d)", count));
            }
        });
        viewModel.getLoginInfo().observe(getViewLifecycleOwner(), loginVo -> {
            if (loginVo != null) {
                userNameText.setText(loginVo.getUsername());
                coinCountText.setText(String.format(Locale.getDefault(), "当前积分: %d", loginVo.getCoinCount()));
            } else {
                userNameText.setText("未登录");
                coinCountText.setVisibility(View.GONE);
            }
        });
        final View setting = binding.settingsLayout;
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(RouterConstants.SETTING_ACTIVITY).navigation();
            }
        });
        EventBus.getDefault().register(this);

        View messageViewGroup = binding.myNotificationsLayout;
        messageViewGroup.setOnClickListener(v -> ARouter.getInstance()
                .build(RouterConstants.REACT_ACTIVITY)
                .withString(RouterConstants.RouterKey.REACT_MODULE_NAME, RouterConstants.ReactPageName.MY_MESSAGE)
                .navigation());
        View collectionVG = binding.myFavoriteLayout;
        collectionVG.setOnClickListener(v -> ARouter.getInstance()
                .build(RouterConstants.REACT_ACTIVITY)
                .withString(RouterConstants.RouterKey.REACT_MODULE_NAME, RouterConstants.ReactPageName.MY_COLLECTION)
                .navigation());
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.triggerMessageCount();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void handleLoginEvent(LoginEvent event) {
        viewModel.getLoginInfo().setValue(event.loginVo);
    }
}