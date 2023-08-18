package com.example.myapplication.main.ui.mine;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.router.RouterConstants;
import com.example.monitor.FPSOverlayView;
import com.example.myapplication.base.login.LoginManager;
import com.example.myapplication.databinding.FragmentMineBinding;


public class MineFragment extends Fragment {

    private FragmentMineBinding binding;

    private ActivityResultLauncher<Intent> launcher;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MineViewModel mineViewModel =
                new ViewModelProvider(this).get(MineViewModel.class);

        binding = FragmentMineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View headerLayout = binding.headerLayout;
        headerLayout.setOnClickListener(view -> {
            if (!LoginManager.Companion.getInstance().isLoggedIn()) {
                LoginManager.Companion.getInstance().showLoginDialog(requireContext());
            } else {
                // TODO
            }
        });
        final TextView textView = binding.profileName;
        mineViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // 在这里处理返回的结果
                        FPSOverlayView fpsOverlayView = new FPSOverlayView(requireContext());
                        fpsOverlayView.setFPS(45);// TODO: fixME
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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}