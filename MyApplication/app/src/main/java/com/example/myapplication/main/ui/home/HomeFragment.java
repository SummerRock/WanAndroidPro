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
import com.example.myapplication.main.ui.home.view.HomeListAdapter;

public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {
    private HomeListAdapter listAdapter;

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
        final SwipeRefreshLayout swipeRefreshLayout = binding.homeFragSwipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.triggerHomeData(0);
            }
        });
        final RecyclerView recyclerView = binding.homeFragRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listAdapter = new HomeListAdapter(null);
        recyclerView.setAdapter(listAdapter);
        viewModel.triggerHomeData(0);
        viewModel.getLiveData().observe(getViewLifecycleOwner(), homeModelVoNetworkModel -> {
            if (homeModelVoNetworkModel.getNetStatus().equals(NetworkModel.NetStatus.SUCCESS)
                    && homeModelVoNetworkModel.data != null) {
                swipeRefreshLayout.setRefreshing(false);
                listAdapter.setData(homeModelVoNetworkModel.data.getDatas());
            }
        });
    }
}