package com.router.civilization.library.ui.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.router.civilization.library.Const
import com.router.civilization.library.R
import com.router.civilization.library.bean.ServiceList
import com.router.civilization.library.recyclerview.RecyclerViewHolder
import com.router.civilization.library.recyclerview.adapter.CommonRecyclerAdapter
import com.router.civilization.library.utils.L
import com.router.civilization.library.utils.StringUtils

/**
 * 首页最新活动adapter
on 2019/8/6.
 */
class MyActsDetailAdapter(mContext: Context, record: ArrayList<ServiceList>, layoutId: Int) : CommonRecyclerAdapter<ServiceList>(mContext, record, layoutId) {

    private var name: String? = null
    private var log: String? = null

    companion object {
        private const val ITEM_TYPE_NORMAL = 1
        private const val ITEM_TYPE_EMPTY = 2
    }

    override fun bindData(holder: RecyclerViewHolder, data: ServiceList, position: Int) {
        when (getItemViewType(position)) {
            ITEM_TYPE_NORMAL -> {
                if (!StringUtils.isEmpty(log)) {
                    holder.setImagePath(R.id.iv_logo, object : RecyclerViewHolder.HolderImageLoader(Const.BASE_URL + log) {
                        override fun loadImage(iv: ImageView, path: String) {
                            var requestOptions = RequestOptions()
                            requestOptions.error(R.mipmap.author_default).placeholder(R.mipmap.author_default)
                            Glide.with(mContext)
                                    .load(path)
                                    .apply(requestOptions)
                                    .into(iv)
                        }
                    })
                }
                if (!StringUtils.isEmpty(name)) {
                    holder.setText(R.id.tv_name, name!!)
                }
                holder.setText(R.id.tv_time, data.date)
                holder.setText(R.id.tv_sign_in_time, if (StringUtils.isEmpty(data.service_start)) "——" else data.service_start)
                holder.setText(R.id.tv_sign_out_time, if (StringUtils.isEmpty(data.service_end)) "——" else data.service_end)
                holder.setText(R.id.tv_service_time, if (data.clock_type == "1") data.service_time.toString() + "(代录)" else data.service_time.toString() + "(自签)")
                holder.setText(R.id.tv_state, data.service_state)
            }
        }


    }

    override fun getItemViewType(position: Int): Int {
        return if (mData.size == 0)
            ITEM_TYPE_EMPTY
        else
            ITEM_TYPE_NORMAL
    }

    override fun getItemCount(): Int {
        return if (mData.size == 0)
            1
        else
            mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return when (viewType) {
            ITEM_TYPE_EMPTY ->
                RecyclerViewHolder(inflaterView(R.layout.empty_view, parent))
            else ->
                RecyclerViewHolder(inflaterView(R.layout.acts_detail_item, parent))
        }

    }


    fun setNameAndLog(name: String?, log: String?) {
        this.name = name.toString()
        this.log = log.toString()
        L.d("setNameAndLog:" + name + log)
    }

}