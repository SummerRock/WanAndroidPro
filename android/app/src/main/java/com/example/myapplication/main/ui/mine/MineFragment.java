package com.example.myapplication.main.ui.mine;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.router.RouterConstants;
import com.example.myapplication.base.fragment.BaseFragment;
import com.example.myapplication.base.login.LoginEvent;
import com.example.myapplication.base.login.LoginManager;
import com.example.myapplication.databinding.FragmentMineBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;


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
        viewModel.getLoginInfo().observe(getViewLifecycleOwner(), loginVo -> {
            if (loginVo != null) {
                userNameText.setText(loginVo.getUsername());
                coinCountText.setText(String.format(Locale.getDefault(), "金币数量: %d", loginVo.getCoinCount()));
            } else {
                userNameText.setText("未登录");
                coinCountText.setVisibility(View.GONE);
            }
        });
        final View setting = binding.settingsLayout;
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                        Uri.parse("package:" + requireActivity().getPackageName()));
//                launcher.launch(intent);
                ARouter.getInstance().build(RouterConstants.SETTING_ACTIVITY).navigation();
            }
        });
        EventBus.getDefault().register(this);

        View messageViewGroup = binding.myNotificationsLayout;
        messageViewGroup.setOnClickListener(v -> ARouter.getInstance()
                .build(RouterConstants.REACT_ACTIVITY)
                .withString(RouterConstants.RouterKey.REACT_MODULE_NAME, RouterConstants.ReactPageName.MY_COLLECTION)
                .navigation());
        View collectionVG = binding.myFavoriteLayout;
        collectionVG.setOnClickListener(v -> ARouter.getInstance()
                .build(RouterConstants.REACT_FRAGMENT_ACTIVITY)
                .withString("componentName", RouterConstants.ReactPageName.MY_COLLECTION)
                .navigation());
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