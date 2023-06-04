package com.example.myapplication.main.ui.home;

import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.example.myapplication.R;
import com.example.myapplication.base.fragment.BaseFragment;
import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

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
        viewModel.triggerHomeData(0);
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<NetworkModel<HomeModelVo>>() {
            @Override
            public void onChanged(NetworkModel<HomeModelVo> homeModelVoNetworkModel) {
                Log.d("xiayan", "");
            }
        });
    }
}