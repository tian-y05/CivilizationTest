package com.router.civilization.library.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.bumptech.glide.Glide
import com.router.civilization.library.R
import com.router.civilization.library.ui.fragment.StationActivityFragment
import com.router.civilization.library.ui.fragment.StationSpotFragment
import com.router.civilization.library.Const
import com.router.civilization.library.base.BaseActivity
import kotlinx.android.synthetic.main.activity_station_detail.*
import java.util.*

/**
 * 所站风貌
on 2019/8/9.
 */
class StationContentDeatailActivity : BaseActivity() {

    private var title: String? = null
    private var logo: String? = null
    private var id: Int? = null
    private var mTitle = arrayOf("实践站", "相关活动")
    private var listFragment = ArrayList<Fragment>()


    override fun layoutId(): Int {
        return R.layout.activity_station_detail
    }

    override fun initData(savedInstanceState: Bundle?) {
        title = intent.getStringExtra("title")
        logo = intent.getStringExtra("logo")
        id = intent.getIntExtra("id", -1)
        initWhiteActionBar(View.VISIBLE, View.OnClickListener {
            finish()
        }, title)

        listFragment.add(StationSpotFragment.getInstance(id.toString()))
        listFragment.add(StationActivityFragment.getInstance(id.toString(),"", Const.STATION))
    }

    override fun initView() {
        Glide.with(this).load(Const.BASE_URL + logo).into(iv_image)
        tv_title.text = title
        slidingTabLayout.setViewPager(viewPager, mTitle, this, listFragment)
    }

    override fun start() {

    }
}