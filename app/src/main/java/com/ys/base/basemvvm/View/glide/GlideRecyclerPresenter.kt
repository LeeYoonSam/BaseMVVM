package com.android.pengtai.baseproject.view.sample.glide

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Single
import org.json.JSONArray
import javax.annotation.Nonnull

class GlideRecyclerPresenter(@Nonnull val mView: GlideRecyclerFragment): IGlideRecyclerPresenter {

    var context: Context = mView.context!!

    // 데이터 요청
    override fun requestGettyDataSingle() {
        val gettyDatas = getGettydataSingleFromFile()

        if(gettyDatas == null) {
            mView.loadEmpty()
        } else {
            mView.loadTestData(gettyDatas)
        }
    }
    private fun getGettydataSingleFromFile(): Single<ArrayList<GettyModel>> {

        return Single.create(
                // let 을 사용해서 this를 넘김 - 일회용으로 사용할때 좋음
                JSONArray(context.assets.open("getty_result.json").bufferedReader().readText()).let {
                    val aryGettyDatas  = Gson().fromJson(it.toString(), Array<GettyModel>::class.java)
                    return Single.fromCallable {
                        ArrayList<GettyModel>(aryGettyDatas.asList())
                    }
                }
        )
    }
}