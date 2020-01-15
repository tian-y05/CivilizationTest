package com.router.civilization.library.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.router.civilization.library.R
import com.router.civilization.library.filterview.FilterView
import com.router.civilization.library.mvp.contract.ActivityListContract
import com.router.civilization.library.mvp.presenter.ActivityListPresenter
import com.router.civilization.library.ui.activity.EventsDetailsActivity
import com.router.civilization.library.ui.adapter.HomeActivityListAdapter
import com.router.civilization.library.base.BaseFragment
import com.router.civilization.library.bean.HomeActivityListBean
import com.router.civilization.library.bean.OrgCataBean
import com.router.civilization.library.bean.OrgSonList
import com.router.civilization.library.recyclerview.adapter.OnItemClickListener
import com.router.civilization.library.utils.IntentUtils
import kotlinx.android.synthetic.main.fragment_article_list.*


/**
 * 多元图文
on 2019/8/5.
 */
class ActivityFragment : BaseFragment(), ActivityListContract.View {

    private var name: String? = null
    private var cateId: String = ""
    private val mPresenter by lazy { ActivityListPresenter() }
    private var orgCataBeanList = ArrayList<OrgCataBean>()
    private var activityList = ArrayList<HomeActivityListBean>()
    private var page: Int = 1
    private var pagesize: Int = 8
    private val mActivityListAdapter by lazy { activity?.let { HomeActivityListAdapter(it, activityList, R.layout.fragment_home_news) } }

    companion object {
        fun getInstance(name: String): ActivityFragment {
            val fragment = ActivityFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.name = name
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article_list
    }

    override fun initView() {
        mPresenter.attachView(this)
        // (真正的)筛选视图点击
        filterView.setOnFilterClickListener(object : FilterView.OnFilterClickListener {
            override fun onFilterClick(select: String) {
                cateId = select
                smartRefresh.autoRefresh()
            }

        })

        smartRefresh.setOnRefreshListener {
            activityList?.clear()
            page = 1
            mPresenter.requestModelsData(cateId, page.toString())
            smartRefresh.setNoMoreData(false)
        }
        smartRefresh.setOnLoadMoreListener {
            page++
            mPresenter.requestModelsData(cateId, page.toString())
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mActivityListAdapter
        mActivityListAdapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                var bundle = Bundle()
                bundle.putString("id",activityList[position].aid)
                activity?.let { IntentUtils.to(it, EventsDetailsActivity::class.java, bundle) }
            }

        })
    }

    override fun lazyLoad() {
        mPresenter.requestActivityCate()
        smartRefresh.autoRefresh()
    }


    override fun showLoading() {
//        loadingDialog.show()
    }

    override fun dismissLoading() {
        smartRefresh.finishRefresh()
        smartRefresh.finishLoadMore()
//        loadingDialog.dismiss()
    }

    override fun setActivityCate(data: List<OrgCataBean>) {
        filterView.visibility = View.VISIBLE
        Log.d("setActivityCate", "data:" + data.toString())
        orgCataBeanList?.addAll(data)
        orgCataBeanList?.let { filterView.setFilterData(activity, it) }
    }

    override fun setActivitySonCate(data: List<OrgSonList>) {

    }

    override fun setModelsData(data: List<HomeActivityListBean>) {
        activityList?.addAll(data)
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

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}