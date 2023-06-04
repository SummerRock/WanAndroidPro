package com.example.myapplication.main.ui.home;

import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.base.fragment.BaseFragment;

public class HomeFragment extends BaseFragment<HomeViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected void performAction() {
        final TextView textView = rootView.findViewById(R.id.text_home);
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
    }
}