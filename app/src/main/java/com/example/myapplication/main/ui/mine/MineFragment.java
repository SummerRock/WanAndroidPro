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
        final TextView textView = binding.profileName;
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void handleLoginEvent(LoginEvent event) {
        if (event.loginVo != null) {
            viewModel.getText().setValue(event.loginVo.getUsername());
        }
    }
}