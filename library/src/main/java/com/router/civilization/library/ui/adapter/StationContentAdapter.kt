package com.router.civilization.library.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.router.civilization.library.recyclerview.RecyclerViewHolder
import com.router.civilization.library.recyclerview.adapter.CommonRecyclerAdapter
import com.router.civilization.library.R
import com.router.civilization.library.Const
import com.router.civilization.library.bean.StationListBean

/**
 * Created by tian
on 2019/8/7.
 */
class StationContentAdapter(mContext: Context, articleList: ArrayList<StationListBean>, layoutId: Int) : CommonRecyclerAdapter<StationListBean>(mContext, articleList, layoutId) {


    override fun bindData(holder: RecyclerViewHolder, data: StationListBean, position: Int) {
        holder.setImagePath(R.id.iv_image,object : RecyclerViewHolder.HolderImageLoader(Const.BASE_URL + data.sta_logo) {
            override fun loadImage(iv: ImageView, path: String) {
                Glide.with(mContext)
                        .load(path)
                        .into(iv)
            }
        })
        holder.setText(R.id.tv_title,data.sta_name)
    }

}