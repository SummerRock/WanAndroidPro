package com.example.myapplication.main;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.myapplication.base.model.NetworkModel;
import com.example.myapplication.main.model.MainTabModel;

import java.util.List;

public class VMMain extends AndroidViewModel {

    private final MutableLiveData<String> triggerTabData = new MutableLiveData<>();

    private final LiveData<NetworkModel<List<MainTabModel>>> mainTabLiveData;

    public VMMain(@NonNull Application application) {
        super(application);
        mainTabLiveData = Transformations.switchMap(triggerTabData,
                s -> MainPageRepository.getInstance().queryMainData());
    }

    public void triggerRequestMainData() {
        triggerTabData.setValue("");
    }

    public LiveData<NetworkModel<List<MainTabModel>>> getMainTabData() {
        return mainTabLiveData;
    }
}
