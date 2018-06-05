package com.ys.base.basemvvm.Network

import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException


open class ApiCallback<M>: DisposableObserver<M>() {

    open fun onSuccess(model: M) {}
    open fun onFailure(code: Int, msg: String) {}
    open fun onFinish() {}


    override fun onComplete() {
        onFinish()
    }

    override fun onNext(model: M) {
        onSuccess(model)
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        if (e is HttpException) {
            val httpException = e
            val code = httpException.code()
            var msg = httpException.message()
            if (code == 504) {
                msg = "gateway timeout"
            }
            if (code == 502 || code == 404) {
                msg = "not found"
            }
            onFailure(code, msg)
        } else {
            onFailure(404, e.message!!)
        }

        onFinish()
    }
}