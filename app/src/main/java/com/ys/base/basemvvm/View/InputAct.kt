package com.ys.base.basemvvm.View

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.ys.base.basemvvm.R
import com.ys.base.basemvvm.ViewModel.InputViewModel
import com.ys.base.basemvvm.databinding.ActInputBinding

class InputAct : AppCompatActivity() {

    private val inputViewModel = InputViewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val binding: ActInputBinding = DataBindingUtil.setContentView(this, R.layout.act_input)
        binding.model = inputViewModel

        inputViewModel.onCreate()
    }
}