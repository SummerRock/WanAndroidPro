package com.example.myapplication.main.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.base.fragment.BaseFragment;
import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {
    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected FragmentHomeBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Override
    protected void performAction() {
        final TextView textView = binding.textHome;
        final SwipeRefreshLayout swipeRefreshLayout = binding.homeFragSwipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("", "");
            }
        });
        final RecyclerView recyclerView = binding.homeFragRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        viewModel.triggerHomeData(0);
        viewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<NetworkModel<HomeModelVo>>() {
            @Override
            public void onChanged(NetworkModel<HomeModelVo> homeModelVoNetworkModel) {
                textView.setText(homeModelVoNetworkModel.getNetStatus().toString());
            }
        });
    }
}