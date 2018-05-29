package com.ys.base.basemvvm.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ys.base.basemvvm.ViewModel.MainViewModel
import android.databinding.DataBindingUtil
import com.ys.base.basemvvm.R
import com.ys.base.basemvvm.ViewModel.CallOtherActivityNavigator
import com.ys.base.basemvvm.databinding.ActMainBinding


/**
 * 참고해서 적용해 보기!!
 *
 * Android(MVVM) pattern with RxJava & Retrofit
 *      blog
 *      https://medium.com/@ah.shubita/android-mvvm-pattern-with-rxjava-retrofit-69fad31dcc7
 *
 *      github
 *      https://github.com/AhmadShubita/MVVM-with-RXJava-and-Retrofit-Users-Generated-Example-
 */
class MainAct : AppCompatActivity(), CallOtherActivityNavigator {

    private val activityModel = MainViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActMainBinding = DataBindingUtil.setContentView(this, R.layout.act_main)
        binding.model = activityModel

        activityModel.onCreate()
        activityModel.setCallActivity(this)
    }

    override fun onResume() {
        super.onResume()
        activityModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        activityModel.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityModel.onDestroy()
    }

    override fun callActivity() {
        startActivity(Intent(this, InputAct::class.java))
    }
}
