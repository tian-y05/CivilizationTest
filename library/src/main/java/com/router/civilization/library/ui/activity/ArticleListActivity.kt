package com.router.civilization.library.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.router.civilization.library.R
import com.router.civilization.library.mvp.contract.ArticleListContract
import com.router.civilization.library.mvp.presenter.ArticleListPresenter
import com.router.civilization.library.ui.adapter.ArticleListAdapter
import com.router.civilization.library.Const
import com.router.civilization.library.base.BaseActivity
import com.router.civilization.library.bean.ArticleListBean
import com.router.civilization.library.recyclerview.adapter.OnItemClickListener
import com.router.civilization.library.utils.IntentUtils
import com.router.civilization.library.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_article_list.*
import java.util.*

/**
 * Created by tian
on 2019/8/7.
 */
class ArticleListActivity : BaseActivity(), ArticleListContract.View {


    private var articleList = ArrayList<ArticleListBean>()
    private var key: Int = -1
    private var name: String? = null
    private var page: Int = 1
    private var pagesize: Int = 8
    private val mArticleListAdapter by lazy { this?.let { ArticleListAdapter(it, articleList) } }
    private val mPresenter by lazy { ArticleListPresenter() }

    override fun layoutId(): Int {
        return R.layout.activity_article_list
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
        recyclerView.adapter = mArticleListAdapter
        smartRefresh.setOnRefreshListener {
            articleList.clear()
            page = 1
            mPresenter.requestModelsData(key.toString(), page.toString())
            smartRefresh.setNoMoreData(false)
        }
        smartRefresh.setOnLoadMoreListener {
            page++
            mPresenter.requestModelsData(key.toString(), page.toString())
        }
        mArticleListAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                var bundle = Bundle()
                bundle.putInt("id", articleList[position].id)
                if (!TextUtils.isEmpty(articleList[position].con_video) || !TextUtils.isEmpty(articleList[position].con_videourl)) {
                    bundle.putString("type", Const.VIDEO)
                } else {
                    bundle.putString("type", Const.ARTICLE)
                }
                IntentUtils.to(this@ArticleListActivity, WebViewDetailActivity::class.java, bundle)
            }

        })
    }

    override fun start() {
        smartRefresh.autoRefresh()
    }


    override fun showLoading() {

    }

    override fun dismissLoading() {
        smartRefresh.finishRefresh()
        smartRefresh.finishLoadMore()
    }

    override fun setModelsData(data: List<ArticleListBean>) {
        articleList.addAll(data)
        mArticleListAdapter.notifyDataSetChanged()
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
        ToastUtils.showShort(this, msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}