package com.router.civilization.library.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.router.civilization.library.R
import com.router.civilization.library.filterview.FilterView
import com.router.civilization.library.mvp.contract.ArticleSortContract
import com.router.civilization.library.mvp.presenter.ArticleSortPresenter
import com.router.civilization.library.ui.activity.ArticleSortDetailsActivity
import com.router.civilization.library.ui.adapter.ActivityListAdapter
import com.router.civilization.library.base.BaseFragment
import com.router.civilization.library.bean.ArticileOrgList
import com.router.civilization.library.bean.OrgCataBean
import com.router.civilization.library.bean.OrgSonList
import com.router.civilization.library.recyclerview.adapter.OnItemClickListener
import com.router.civilization.library.utils.IntentUtils
import com.router.civilization.library.utils.ToastUtils
import kotlinx.android.synthetic.main.fragment_article_sort.*

/**
 * 组织列表
on 2019/8/5.
 */
class ArticleSortFragment : BaseFragment(), ArticleSortContract.View {


    private var name: String? = null
    private var orgCataBeanList = ArrayList<OrgCataBean>()
    private var page: Int = 1
    private var pagesize: Int = 8
    private var cateId: String = ""
    private var activityList = ArrayList<ArticileOrgList>()
    private val mActivityListAdapter by lazy { activity?.let { ActivityListAdapter(it, activityList, R.layout.activity_list_item) } }
    private val mPresenter by lazy { ArticleSortPresenter() }


    companion object {
        fun getInstance(name: String): ArticleSortFragment {
            val fragment = ArticleSortFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.name = name
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_article_sort
    }

    override fun initView() {
        mPresenter.attachView(this)
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

        filterView.setOnFilterClickListener(object : FilterView.OnFilterClickListener {
            override fun onFilterClick(select: String) {
                cateId = select
                smartRefresh.autoRefresh()
            }

        })

        mActivityListAdapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                var bundle = Bundle()
                bundle.putString("id", activityList[position].id)
                bundle.putString("type", "home")

                activity?.let { IntentUtils.to(it, ArticleSortDetailsActivity::class.java, bundle) }
            }

        })
    }

    override fun lazyLoad() {
        mPresenter.requestActivityCate()
        smartRefresh.autoRefresh()
    }


    override fun showLoading() {
    }

    override fun dismissLoading() {
        smartRefresh.finishRefresh()
        smartRefresh.finishLoadMore()
    }

    override fun setActivityCate(data: List<OrgCataBean>) {
        filterView.visibility = View.VISIBLE
        orgCataBeanList?.addAll(data)
        orgCataBeanList?.let { filterView.setFilterData(activity, it) }
    }

    override fun setActivitySonCate(data: List<OrgSonList>) {
    }

    override fun setModelsData(data: List<ArticileOrgList>) {
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