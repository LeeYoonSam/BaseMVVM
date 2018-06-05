package com.ys.base.basemvvm.Network

import android.util.Log
import com.ys.base.basemvvm.Model.GithubModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.reflect.KClass

class APIHelper {

    private lateinit var apiInterface: APIInterface
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    companion object {
        fun getInstance(): APIHelper {
            return LazyHolder.INSTANCE
        }
    }

    fun setAPIInterface(klass: KClass<APIInterface>) {
        this.apiInterface = APIClient.getClient().create(klass.java)
    }

    fun getSample(): Single<GithubModel>? {
        Log.d("API Helper", "call getSample")

        return apiInterface.getGithubSample()
                .subscribeOn(Schedulers.io())
    }

    val callback: ApiCallback<String> =
            object : ApiCallback<String>() {
                override fun onSuccess(model: String) {
                    Log.d("", model)
                }
            }

    fun getWikiTest() {
        compositeDisposable.add(apiInterface.getTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(callback))

    }

    class LazyHolder {
        companion object {
            val INSTANCE: APIHelper = APIHelper()
        }
    }

}