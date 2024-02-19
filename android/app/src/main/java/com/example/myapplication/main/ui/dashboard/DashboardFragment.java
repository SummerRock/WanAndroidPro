package com.example.myapplication.main.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        viewModel.getCounterModelLiveData().observe(getViewLifecycleOwner(), counterModel -> {
            textView.setText(String.valueOf(counterModel.getCount()));
        });
        binding.testIncrementBtn.setOnClickListener(v -> viewModel.processIntent(new CounterIntent.Increment()));
        binding.testDecrementBtn.setOnClickListener(v -> viewModel.processIntent(new CounterIntent.Decrement()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}