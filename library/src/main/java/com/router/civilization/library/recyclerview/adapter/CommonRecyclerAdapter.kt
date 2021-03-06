package com.router.civilization.library.recyclerview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.router.civilization.library.recyclerview.MultipleType
import com.router.civilization.library.recyclerview.RecyclerViewHolder

/**
 * desc: 通用的 Adapter
 */

abstract class CommonRecyclerAdapter<T>(var mContext: Context, var mData: ArrayList<T>, //条目布局
                                        private var mLayoutId: Int) : RecyclerView.Adapter<RecyclerViewHolder>() {
    protected var mInflater: LayoutInflater? = null
    private var mTypeSupport: MultipleType<T>? = null

    //使用接口回调点击事件
    private var mItemClickListener: OnItemClickListener? = null

    //使用接口回调点击事件
    private var mItemLongClickListener: OnItemLongClickListener? = null

    init {
        mInflater = LayoutInflater.from(mContext)
    }

    //需要多布局
    constructor(context: Context, data: ArrayList<T>, typeSupport: MultipleType<T>) : this(context, data, -1) {
        this.mTypeSupport = typeSupport
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        if (mTypeSupport != null) {
            //需要多布局
            mLayoutId = viewType
        }
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return RecyclerViewHolder(view!!)
    }

    override fun getItemViewType(position: Int): Int {
        //多布局问题
        return mTypeSupport?.getLayoutId(mData[position], position)
                ?: super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        if (mData == null || (mData != null && mData.size == 0))
            return
        //绑定数据
        bindData(holder, mData[position], position)

        //条目点击事件
        mItemClickListener?.let {
            holder.itemView.setOnClickListener { mItemClickListener!!.onItemClick(mData[position], position) }
        }

        //长按点击事件
        mItemLongClickListener?.let {
            holder.itemView.setOnLongClickListener { mItemLongClickListener!!.onItemLongClick(mData[position], position) }
        }
    }

    /**
     * 将必要参数传递出去
     *
     * @param holder
     * @param data
     * @param position
     */
    protected abstract fun bindData(holder: RecyclerViewHolder, data: T, position: Int)

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mItemClickListener = itemClickListener
    }

    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener) {
        this.mItemLongClickListener = itemLongClickListener
    }


    /**
     * 加载布局
     */
    fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return view ?: View(parent.context)
    }

    var childClickListener: OnItemChildListener? = null
    fun setOnItemChildListener(clickListener: OnItemChildListener) {
        this.childClickListener = clickListener
    }

    interface OnItemChildListener {
        fun onItemChidClick(v: View, position: Int)
    }

    fun setOnChildClickListener(view: View, position: Int) {
        view.setOnClickListener {
            childClickListener?.onItemChidClick(it, position)
        }
    }
}
