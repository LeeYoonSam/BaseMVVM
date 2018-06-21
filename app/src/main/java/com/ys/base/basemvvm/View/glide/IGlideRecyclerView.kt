package com.android.pengtai.baseproject.view.sample.glide

import io.reactivex.Single

interface IGlideRecyclerView {
    fun loadTestData(alTestData: Single<ArrayList<GettyModel>>)
    fun loadEmpty()
}