package com.example.myapplication.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.common.mainModule.LogUtils;
import com.example.myapplication.base.view.BaseMultiStateConstant;
import com.example.myapplication.base.view.BaseMultiStateView;

public abstract class BaseFragment<V extends ViewModel, VB extends ViewBinding> extends Fragment {
    protected V viewModel;

    protected VB binding;

    protected abstract Class<V> getViewModelClass();

    protected abstract VB getBinding(LayoutInflater inflater, ViewGroup container);

    protected BaseMultiStateView baseMultiStateView;

    protected void changeInnerStatus(@BaseMultiStateConstant int state) {
        if (baseMultiStateView != null) {
            baseMultiStateView.show(state);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getBinding(inflater, container);
        View base = binding.getRoot();
        if (base instanceof ViewGroup) {
            baseMultiStateView = new BaseMultiStateView(requireContext());
            ((ViewGroup) base).addView(baseMultiStateView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        } else {
            LogUtils.w("wrong view type");
        }
        return base;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(getViewModelClass());
        performAction();
    }

    protected abstract void performAction();
}

