package com.example.myapplication.main.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.common.mainModule.LogUtils
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.main.ui.dashboard.CounterIntent.Decrement
import com.example.myapplication.main.ui.dashboard.model.CounterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment() {
    private var binding: FragmentDashboardBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding!!.getRoot()
        val textView = binding!!.textDashboard
        viewModel.counterModelLiveData.observe(getViewLifecycleOwner()) { counterModel: CounterModel ->
            textView.text = counterModel.count.toString()
        }
        binding!!.testIncrementBtn.setOnClickListener { v: View? ->
            viewModel.processIntent(
                CounterIntent.Increment()
            )
        }
        binding!!.testDecrementBtn.setOnClickListener { v: View? ->
            viewModel.processIntent(
                Decrement()
            )
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        learn()
    }

    fun learn () {
        MainScope().launch {
            ioCode1()
        }
    }

    private suspend fun ioCode1() {
        withContext(Dispatchers.IO) {
            LogUtils.v("io opera 1");
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}