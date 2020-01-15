package com.router.civilization.library.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.router.civilization.library.Const
import com.router.civilization.library.R
import com.router.civilization.library.ui.fragment.ActivityFragment
import com.router.civilization.library.ui.fragment.ArticleSortFragment
import com.router.civilization.library.ui.fragment.StationContentFragment
import com.router.civilization.library.ui.fragment.StationListFragment
import com.router.civilization.library.base.BaseActivity
import com.router.civilization.library.eventbus.EventBusMessage
import com.router.civilization.library.utils.IntentUtils
import kotlinx.android.synthetic.main.activity_article_sort.*
import java.util.*

/**
 * 活动分类
on 2019/8/7.
 */
class ArticleSortActivity : BaseActivity() {

    private var mTitle = emptyArray<String>()
    private var name: String? = null
    private var type: String? = null
    private var key: Int = -1
    private var listFragment = ArrayList<Fragment>()


    override fun layoutId(): Int {
        return R.layout.activity_article_sort
    }

    override fun initData(savedInstanceState: Bundle?) {
        key = intent.getIntExtra("key", -1)
        name = intent.getStringExtra("name")
        type = intent.getStringExtra("type")
        mTitle = intent.getStringArrayExtra("title")

        when (type) {
            "station" -> {
                listFragment.add(StationContentFragment.getInstance(mTitle[0]))
                listFragment.add(StationListFragment.getInstance(mTitle[1]))
            }
            "polymerize" -> {
                listFragment.add(ArticleSortFragment.getInstance(mTitle[0]))
                listFragment.add(ActivityFragment.getInstance(mTitle[1]))
            }
        }


    }

    override fun initView() {
        initWhiteActionBar(View.VISIBLE, View.OnClickListener {
            finish()
        }, name)

        slidingTabLayout.setViewPager(viewPager, mTitle, this, listFragment)
        fab_button.visibility = View.VISIBLE
        fab_button.setOnClickListener {
            IntentUtils.to(this, OrgSearchActivity::class.java)
        }
    }

    override fun start() {

    }

    override fun onReceiveEvent(event: EventBusMessage<Any>) {
        when (event.code) {
            Const.GO_LOGIN_VOL, Const.GO_LOGIN_ORG -> {
                finish()
            }
        }
    }
}