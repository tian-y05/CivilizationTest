package com.router.civilization.library.ui.adapter

import android.content.Context
import android.view.View
import com.router.civilization.library.R
import com.router.civilization.library.bean.IdentityInfoBean
import com.router.civilization.library.recyclerview.RecyclerViewHolder
import com.router.civilization.library.recyclerview.adapter.CommonRecyclerAdapter

/**
 * Created by tian
on 2019/8/7.
 */
class FindIdentityAdapter(mContext: Context, articleList: ArrayList<IdentityInfoBean>, layoutId: Int) : CommonRecyclerAdapter<IdentityInfoBean>(mContext, articleList, layoutId) {

    private var curPosition = -1

    override fun bindData(holder: RecyclerViewHolder, data: IdentityInfoBean, position: Int) {
        holder.setText(R.id.tv_name, data.name)
        holder.setText(R.id.tv_sex, data.sex)
        holder.setText(R.id.tv_card_number, data.certificates_number)
        holder.setText(R.id.tv_tel, data.phone)
        holder.setText(R.id.tv_account, "NO." + data.volunteer_number)
        holder.setText(R.id.tv_area, data.area)
        if(curPosition == position){
            holder.setViewVisibility(R.id.iv_select, View.VISIBLE)
        }else{
            holder.setViewVisibility(R.id.iv_select, View.GONE)
        }
    }

    fun setCurrentPos(position: Int) {
        this.curPosition = position
        notifyDataSetChanged()
    }


}