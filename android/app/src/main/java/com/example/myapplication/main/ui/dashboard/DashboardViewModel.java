package com.example.myapplication.main.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.main.ui.dashboard.model.CounterModel;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<CounterModel> counterModelLiveData = new MutableLiveData<>(new CounterModel(0));

    public LiveData<CounterModel> getCounterModelLiveData() {
        return counterModelLiveData;
    }

    public void processIntent(CounterIntent intent) {
        CounterModel currentModel = counterModelLiveData.getValue();
        CounterModel newModel = null;

        if (intent instanceof CounterIntent.Increment) {
            newModel = new CounterModel(currentModel.getCount() + 1);
        } else if (intent instanceof CounterIntent.Decrement) {
            newModel = new CounterModel(currentModel.getCount() - 1);
        }

        if (newModel != null) {
            counterModelLiveData.setValue(newModel);
        }
    }
}