package com.router.civilization.library.ui.adapter

import android.content.Context
import android.text.TextUtils
import com.router.civilization.library.recyclerview.RecyclerViewHolder
import com.router.civilization.library.recyclerview.adapter.CommonRecyclerAdapter
import com.router.civilization.library.R
import com.router.civilization.library.bean.OrgCataBean

/**
 * 筛选adapter
on 2019/8/6.
 */
class FilterCateTitleListAdapter(mContext: Context, orgSonBeanList: ArrayList<OrgCataBean>, layoutId: Int) : CommonRecyclerAdapter<OrgCataBean>(mContext, orgSonBeanList, layoutId) {


    override fun bindData(holder: RecyclerViewHolder, data: OrgCataBean, position: Int) {
        holder.setText(R.id.tv_name, if (data.select_name != null && !TextUtils.isEmpty(data.select_name)) {data.select_name} else data.cate_name)
    }


}