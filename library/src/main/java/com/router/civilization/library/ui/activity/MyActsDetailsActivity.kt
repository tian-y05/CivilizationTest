package com.router.civilization.library.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.router.civilization.library.R
import com.router.civilization.library.mvp.contract.MyActsContract
import com.router.civilization.library.mvp.presenter.MyActsPresenter
import com.router.civilization.library.ui.adapter.MyActsDetailAdapter
import com.router.civilization.library.base.BaseActivity
import com.router.civilization.library.bean.ActivityService
import com.router.civilization.library.bean.ClockCardBean
import com.router.civilization.library.bean.MyActivityBean
import com.router.civilization.library.bean.ServiceList
import com.router.civilization.library.utils.SPUtil
import kotlinx.android.synthetic.main.activity_article_list.*
import java.util.*

/**
 * 服务详情
on 2019/8/26.
 */
class MyActsDetailsActivity : BaseActivity(), MyActsContract.View {


    private val mPresenter by lazy { MyActsPresenter() }
    private var recordList = ArrayList<ServiceList>()
    private val mListAdapter by lazy { MyActsDetailAdapter(this, recordList, R.layout.acts_detail_item) }

    override fun layoutId(): Int {
        return R.layout.activity_article_list
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun initView() {
        mPresenter.attachView(this)
        initWhiteActionBar(View.VISIBLE, View.OnClickListener {
            finish()
        }, getString(R.string.service_detail))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mListAdapter
    }

    override fun start() {
        var map = HashMap<String, String>()
        map.put("userid", SPUtil.get(this, "userId", "").toString())
        map.put("aid", intent.getStringExtra("aid"))
        mPresenter.getActivityService(map)
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun showError(msg: String, errorCode: Int) {
    }

    override fun setModelsData(data: List<MyActivityBean>) {
    }

    override fun activityServiceDetail(data: ActivityService) {
        data?.list?.let { recordList.addAll(it) }
        mListAdapter.setNameAndLog(data.user_name,data.user_logo)
        mListAdapter.notifyDataSetChanged()
    }

    override fun removeSignResult(date: String) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun checkCardResult(date: ClockCardBean) {
    }
}