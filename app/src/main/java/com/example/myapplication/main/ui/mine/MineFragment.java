package com.example.myapplication.main.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.base.login.LoginManager;
import com.example.myapplication.databinding.FragmentMineBinding;


public class MineFragment extends Fragment {

    private FragmentMineBinding binding;

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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}