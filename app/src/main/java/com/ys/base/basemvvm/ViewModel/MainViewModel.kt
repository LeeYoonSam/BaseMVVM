package com.ys.base.basemvvm.ViewModel

import android.databinding.ObservableField
import android.view.View

class MainViewModel : BaseViewModel() {

    lateinit var callback: CallOtherActivityNavigator
    var helloText = ObservableField<String>()

    override fun onCreate() {
        helloText.set("onCreate")
    }

    override fun onResume() {
        helloText.set("onResume")
    }

    override fun onPause() {
        helloText.set("onPause")
    }

    override fun onDestroy() {
        helloText.set("onDestroy")
    }

    fun showCurrentTime() {
        helloText.set(System.currentTimeMillis().toString())
    }

    override fun setCallActivity(callback: CallOtherActivityNavigator) {
        super.setCallActivity(callback)

        this.callback =  callback
    }

    val currentTime2ClickListener: View.OnClickListener = View.OnClickListener { showCurrentTime() }

    fun nextActivity() {
        callback.callActivity()
    }
}