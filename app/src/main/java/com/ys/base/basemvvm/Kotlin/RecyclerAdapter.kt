package com.ys.base.basemvvm.Kotlin

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.ViewGroup
import com.ys.base.basemvvm.R

class RecyclerAdapter: RecyclerView.Adapter<HolderTest>() {

    val items = ArrayList<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderTest {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // let / with 활용하기
    override fun onBindViewHolder(holder: HolderTest, position: Int) {
        items[position].let { repo ->

            with(holder) {

//                // 합성 프로퍼티인 ivItemRepositoryProfile로 뷰 인스턴스에 접근합니다.
//                GlideApp.with(holder.itemView.context)
//                        .load(repo.owner.avatarUrl)
//                        .placeholder(placeholder)
//                        .into(ivItemRepositoryProfile)
//
//                // 합성 프로퍼티인 tvItemRepositoryName으로 뷰 인스턴스에 접근합니다.
//                etName.text = repo.fullName
//
//                // 합성 프로퍼티인 tvItemRepositoryLanguage로 뷰 인스턴스에 접근합니다.
//                etEmail.text = if (TextUtils.isEmpty(repo.language))
//                    containerView.context.getText(R.string.no_language_specified)
//                else
//                    repo.language
//
//                containerView.setOnClickListener { listener?.onItemClick(repo) }
            }
        }
    }
}