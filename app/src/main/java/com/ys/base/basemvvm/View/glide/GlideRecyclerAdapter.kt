package com.android.pengtai.baseproject.view.sample.glide

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ys.base.basemvvm.R
import kotlinx.android.synthetic.main.cell_glide_recycler.view.*

class GlideRecyclerAdapter (private val gettyDatas: List<GettyModel>, private val clickListener: (GettyModel) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // lateinit 으로 선언하고 나중에 초기화하기
    lateinit var context: Context

    // 뷰홀더 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.cell_glide_recycler, parent, false)
        return GlideViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gettyDatas.size
    }

    // 리사이클러뷰 그릴때 뷰홀더를 사용해서 데이터와 뷰를 바로 바인딩
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GlideViewHolder).bind(context, gettyDatas[position], clickListener)
    }

    // 뷰홀더 바인딩
    class GlideViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, gettyModel: GettyModel, clickListener: (GettyModel) -> Unit) {

            itemView.tvTitle.text = gettyModel.title

            // 글라이드 이미지 보여주기
            Glide.with(context)
                    .load(gettyModel.image)
                    .into(itemView.ivImage)

            // 클릭 콜백 액션을 Unit으로 생성
            itemView.setOnClickListener { clickListener?.invoke(gettyModel) }
        }
    }
}