package com.router.civilization.library.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.router.civilization.library.R
import com.router.civilization.library.mvp.contract.ArticleListContract
import com.router.civilization.library.mvp.presenter.ArticleListPresenter
import com.router.civilization.library.ui.adapter.ArticleListAdapter
import com.router.civilization.library.Const
import com.router.civilization.library.base.BaseFragment
import com.router.civilization.library.bean.ArticleListBean
import com.router.civilization.library.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_station_list.*

/**
 * 所站风貌
on 2019/8/5.
 */
class StationListFragment : BaseFragment(), ArticleListContract.View {

    private var name: String? = null
    private var activityList = ArrayList<ArticleListBean>()
    private var page: Int = 1
    private var pagesize: Int = 8
    private val mActivityListAdapter by lazy { activity?.let { ArticleListAdapter(it, activityList) } }
    private val mPresenter by lazy { ArticleListPresenter() }

    companion object {
        fun getInstance(name: String): StationListFragment {
            val fragment = StationListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.name = name
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_station_list
    }

    override fun initView() {
        mPresenter.attachView(this)
        smartRefresh.setOnRefreshListener {
            activityList?.clear()
            page = 1
            mPresenter.requestModelsData(Const.STATION_KEY, page.toString())
            smartRefresh.setNoMoreData(false)
        }
        smartRefresh.setOnLoadMoreListener {
            page++
            mPresenter.requestModelsData(Const.STATION_KEY, page.toString())
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mActivityListAdapter

    }

    override fun lazyLoad() {
        smartRefresh.autoRefresh()
    }


    override fun showLoading() {
    }

    override fun dismissLoading() {
        smartRefresh.finishRefresh()
        smartRefresh.finishLoadMore()
    }

    override fun setModelsData(data: List<ArticleListBean>) {
        activityList.addAll(data)
        mActivityListAdapter?.notifyDataSetChanged()

        if (page == 1) {
            smartRefresh.finishRefresh()
        } else {
            if ((data != null && data.isEmpty())) {
                smartRefresh.finishLoadMoreWithNoMoreData()
            } else if (data != null && data.size < pagesize) {
                smartRefresh.finishLoadMore()
            }
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        ToastUtils.showShort(activity,msg)
    }
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}