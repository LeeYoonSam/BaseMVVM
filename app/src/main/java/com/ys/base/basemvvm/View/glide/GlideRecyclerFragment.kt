package com.android.pengtai.baseproject.view.sample.glide

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ys.base.basemvvm.R
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_glide_recycler.*

class GlideRecyclerFragment: Fragment(), IGlideRecyclerView {

    private lateinit var glideRecyclerPresenter: GlideRecyclerPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater!!.inflate(R.layout.fragment_glide_recycler, null)
        return rootView!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        rvGetty.layoutManager = GridLayoutManager(activity, 3)
        rvGetty?.hasFixedSize()

        glideRecyclerPresenter = GlideRecyclerPresenter(this)
        loadData()
    }

    private fun loadData() {
        glideRecyclerPresenter?.requestGettyDataSingle()
    }

    override fun loadEmpty() {

    }

    override fun loadTestData(alTestData: Single<ArrayList<GettyModel>>) {
        alTestData.retry(2).subscribe { data ->
            rvGetty.adapter = GlideRecyclerAdapter(data, { gettyItem : GettyModel -> partItemClicked(gettyItem) })
        }
    }

    private fun partItemClicked(gettyItem : GettyModel) {
        Toast.makeText(activity, "Clicked: ${ gettyItem.title }", Toast.LENGTH_LONG).show()

        // Todo: Add Action
    }
}