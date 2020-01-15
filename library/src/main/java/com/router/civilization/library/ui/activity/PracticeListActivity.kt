package com.router.civilization.library.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.router.civilization.library.R
import com.router.civilization.library.mvp.contract.PracticeListContract
import com.router.civilization.library.mvp.presenter.PracticeListPresenter
import com.router.civilization.library.ui.adapter.PracticeListAdapter
import com.router.civilization.library.Const
import com.router.civilization.library.base.BaseActivity
import com.router.civilization.library.bean.PracticeBaseBean
import com.router.civilization.library.recyclerview.adapter.OnItemClickListener
import com.router.civilization.library.utils.IntentUtils
import com.router.civilization.library.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_practice_list.*


/**
 * 实践基地
 */
class PracticeListActivity : BaseActivity(), PracticeListContract.View {


    private var key: Int = -1
    private var name: String? = null
    private var practiceList = ArrayList<PracticeBaseBean>()
    private var page: Int = 1
    private var pagesize: Int = 8
    private val mPracticeListAdapter by lazy { this?.let { PracticeListAdapter(it, practiceList, R.layout.fragment_station_content) } }
    private val mPresenter by lazy { PracticeListPresenter() }

    override fun layoutId(): Int {
        return R.layout.activity_practice_list
    }

    override fun initData(savedInstanceState: Bundle?) {
        key = intent.getIntExtra("key", -1)
        name = intent.getStringExtra("name")
    }

    override fun initView() {
        mPresenter.attachView(this)
        initWhiteActionBar(View.VISIBLE, View.OnClickListener {
            finish()
        }, name)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mPracticeListAdapter
        smartRefresh.setOnRefreshListener {
            practiceList.clear()
            page = 1
            mPresenter.requestModelsData(page.toString())
            smartRefresh.setNoMoreData(false)
        }
        smartRefresh.setOnLoadMoreListener {
            page++
            mPresenter.requestModelsData(page.toString())
        }

        mPracticeListAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                var bundle = Bundle()
                bundle.putInt("id", practiceList[position].id)
                bundle.putString("type", Const.PRACTICE)
                IntentUtils.to(this@PracticeListActivity, WebViewDetailActivity::class.java,bundle)
            }

        })
    }

    override fun start() {
        smartRefresh.autoRefresh()
    }

    override fun setModelsData(data: List<PracticeBaseBean>) {
        practiceList.addAll(data)
        mPracticeListAdapter.notifyDataSetChanged()
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
        ToastUtils.showShort(this,msg)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
        smartRefresh.finishRefresh()
        smartRefresh.finishLoadMore()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}