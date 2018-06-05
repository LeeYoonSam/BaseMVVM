package com.ys.base.basemvvm.Network

import android.os.IInterface

interface APISetInterface<in T: IInterface> {
    fun setInterface(apiInterface: T)
}