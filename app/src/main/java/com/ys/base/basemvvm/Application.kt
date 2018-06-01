package com.ys.base.basemvvm

import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.facebook.stetho.DumperPluginsProvider
import com.facebook.stetho.Stetho
import io.realm.Realm

// minSdkVersion 20 이하 일때 MultiDexApplication 을 상속받고 MultiDex.install(this)를 추가 해야함
class Application: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        Realm.init(this)
        Stetho.initializeWithDefaults(this)
    }
}