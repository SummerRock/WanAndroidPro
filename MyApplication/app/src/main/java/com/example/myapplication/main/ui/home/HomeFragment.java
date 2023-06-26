package com.example.myapplication.main.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.base.fragment.BaseFragment;
import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.main.ui.home.view.HomeListAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
        final SmartRefreshLayout refreshLayout = binding.homeFragRefreshLayout;
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.triggerHomeData(0);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int currentIndex = viewModel.getMutableLiveData().getValue();
                viewModel.triggerHomeData(++currentIndex);
            }
        });
        final RecyclerView recyclerView = binding.homeFragRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        // 创建分割线对象
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL);
        // 设置分割线样式和颜色（可选）
        // dividerItemDecoration.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider));
        // 将分割线添加到RecyclerView
        recyclerView.addItemDecoration(dividerItemDecoration);

        listAdapter = new HomeListAdapter(null);
        recyclerView.setAdapter(listAdapter);
        viewModel.triggerHomeData(0);
        viewModel.getLiveData().observe(getViewLifecycleOwner(), homeModelVoNetworkModel -> {
            if (homeModelVoNetworkModel.getNetStatus().equals(NetworkModel.NetStatus.SUCCESS)
                    && homeModelVoNetworkModel.data != null) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                listAdapter.setData(homeModelVoNetworkModel.data.getDatas());
                refreshLayout.setEnableLoadMore(!homeModelVoNetworkModel.data.getOver());
            }
        });
    }
}