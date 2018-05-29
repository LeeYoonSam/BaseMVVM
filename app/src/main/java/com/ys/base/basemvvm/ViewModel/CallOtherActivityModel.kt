package com.ys.base.basemvvm.ViewModel

class CallOtherActivityModel(val navigator: CallOtherActivityNavigator) {

    fun callActivity() {
        navigator.callActivity()
    }
}