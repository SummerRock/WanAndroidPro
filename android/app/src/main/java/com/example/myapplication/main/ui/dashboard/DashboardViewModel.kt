package com.example.myapplication.main.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.main.ui.dashboard.CounterIntent.Decrement
import com.example.myapplication.main.ui.dashboard.model.CounterModel

class DashboardViewModel : ViewModel() {
    val counterModelLiveData = MutableLiveData(CounterModel(0))
    fun getCounterModelLiveData(): LiveData<CounterModel> {
        return counterModelLiveData
    }

    fun processIntent(intent: CounterIntent?) {
        val currentModel = counterModelLiveData.getValue()
        var newModel: CounterModel? = null
        if (intent is CounterIntent.Increment) {
            newModel = CounterModel(currentModel!!.count + 1)
        } else if (intent is Decrement) {
            newModel = CounterModel(currentModel!!.count - 1)
        }
        if (newModel != null) {
            counterModelLiveData.value = newModel
        }
    }
}