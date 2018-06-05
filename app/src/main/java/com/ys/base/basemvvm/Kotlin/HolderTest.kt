package com.ys.base.basemvvm.Kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ys.base.basemvvm.R
import kotlinx.android.synthetic.main.act_input.view.*

class HolderTest(parent: ViewGroup) : RecyclerView.ViewHolder (
        LayoutInflater.from(parent.context)
                .inflate(R.layout.act_input, parent, false)) {

    // 각 뷰의 인스턴스를 저장하는 프로퍼티를 추가합니다.
    // 생성자가 호출되는 시점에 뷰의 인스턴스가 할당됩니다.
    val etName = itemView.etName
    val etEmail = itemView.etEmail

}