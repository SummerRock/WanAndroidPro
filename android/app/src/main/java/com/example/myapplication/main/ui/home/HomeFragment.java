package com.example.myapplication.main.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.base.favorite.CollectChangeEvent;
import com.example.myapplication.base.fragment.BaseFragment;
import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.base.view.BaseMultiStateConstant;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.main.ui.home.model.HomeBannerVo;
import com.example.myapplication.main.ui.home.model.HomeBannerWrap;
import com.example.myapplication.main.ui.home.model.HomeModelVo;
import com.example.myapplication.main.ui.home.view.HomeBannerViewBinder;
import com.example.myapplication.main.ui.home.view.HomeItemViewBinder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;


public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> {

    // private final HomeListAdapter listAdapter = new HomeListAdapter(null);

    private final List<Object> displayDataList = new ArrayList<>();
    private final MultiTypeAdapter adapter = new MultiTypeAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayDataList.add(0, null);
    }

    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected FragmentHomeBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @Subscribe
    public void onCollectSuccessEvent(CollectChangeEvent event) {
        List<?> dataList = adapter.getItems();
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i) instanceof HomeModelVo.DatasDTO) {
                HomeModelVo.DatasDTO dataDTO = (HomeModelVo.DatasDTO) dataList.get(i);
                if (dataDTO.getId().equals(event.id)) {
                    dataDTO.setCollect(event.status);
                    adapter.notifyItemChanged(i);
                    break;
                }
            }
        }
    }

    @Override
    protected void performAction() {
        EventBus.getDefault().register(this);
        final SmartRefreshLayout refreshLayout = binding.homeFragRefreshLayout;
        refreshLayout.setRefreshFooter(new ClassicsFooter(requireContext()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.triggerHomeData(0);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int currentIndex = viewModel.getMutablePageIndexData().getValue();
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

        adapter.register(HomeModelVo.DatasDTO.class, new HomeItemViewBinder(null));
        adapter.register(HomeBannerWrap.class, new HomeBannerViewBinder(null));

        recyclerView.setAdapter(adapter);
        viewModel.triggerHomeData(0);
        viewModel.getHomeListLiveData().observe(getViewLifecycleOwner(), homeModelVoNetworkModel -> {
            if (homeModelVoNetworkModel.getNetStatus().equals(NetworkModel.NetStatus.SUCCESS)
                    && homeModelVoNetworkModel.data != null) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                if (homeModelVoNetworkModel.data.getCurPage() == 1) {
                    Object first = displayDataList.get(0);
                    displayDataList.clear();
                    displayDataList.add(first);
                    displayDataList.addAll(homeModelVoNetworkModel.data.getDatas());
                    adapter.setItems(displayDataList);
                    adapter.notifyDataSetChanged();
                    // listAdapter.refreshData(homeModelVoNetworkModel.data.getDatas());
                } else {
                    int originCount = adapter.getItemCount();
                    int count = homeModelVoNetworkModel.data.getDatas().size();
                    displayDataList.addAll(homeModelVoNetworkModel.data.getDatas());
                    adapter.setItems(displayDataList);
                    adapter.notifyItemRangeInserted(originCount + 1, count);
                    // listAdapter.addData(homeModelVoNetworkModel.data.getDatas());
                }
                refreshLayout.setEnableLoadMore(!homeModelVoNetworkModel.data.getOver());
                changeInnerStatus(BaseMultiStateConstant.hide);
            } else if (homeModelVoNetworkModel.getNetStatus().equals(NetworkModel.NetStatus.LOADING)) {
                if (adapter.getItemCount() == 0) {
                    changeInnerStatus(BaseMultiStateConstant.Loading);
                }
            } else {
                changeInnerStatus(BaseMultiStateConstant.NetworkFail);
            }
        });
        viewModel.getHomeBannerLiveData().observe(getViewLifecycleOwner(), new Observer<NetworkModel<List<HomeBannerVo>>>() {
            @Override
            public void onChanged(NetworkModel<List<HomeBannerVo>> listNetworkModel) {
                if (listNetworkModel.getNetStatus().equals(NetworkModel.NetStatus.SUCCESS) && listNetworkModel.data != null) {
                    displayDataList.set(0, new HomeBannerWrap(listNetworkModel.data));
                    adapter.notifyItemChanged(0);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this); // 解注册
    }
}