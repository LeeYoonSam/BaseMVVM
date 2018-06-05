package com.ys.base.basemvvm.ViewModel

import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.ys.base.basemvvm.Model.GithubModel
import com.ys.base.basemvvm.Network.APIClient
import com.ys.base.basemvvm.Network.APIHelper
import com.ys.base.basemvvm.Network.APIInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {

    lateinit var api: APIHelper
    lateinit var callback: CallOtherActivityNavigator
    var helloText = ObservableField<String>()

    var sampleData: GithubModel? = null

    override fun onCreate() {

        helloText.set("onCreate")

        api = APIHelper.getInstance()
        api.setAPIInterface(APIInterface::class)

//        api.getSample()?.run {
//            observeOn(AndroidSchedulers.mainThread())
//            subscribe { data ->
//                sampleData = data
//                testSample()
//
//            }
//        }

        api.getWikiTest()

//        api.getInitCodeTest()

    }

    fun testSample() {
        val strData = sampleData?.let {
            """
                | current_user_url $it.current_user_url
                | current_user_authorizations_html_url $it.current_user_authorizations_html_url
                | authorizations_url $it.authorizations_url
                | code_search_url $it.code_search_url
                | commit_search_url $it.commit_search_url
                """.trimMargin("|")
        }

        Log.d(this::class.java.name, strData)
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