package com.ys.base.basemvvm.ViewModel

open class BaseViewModel {
    open fun onCreate() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onDestroy() {}
    open fun setCallActivity(callback: CallOtherActivityNavigator) {}
}