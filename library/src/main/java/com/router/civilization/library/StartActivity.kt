package com.router.civilization.library

import android.os.Bundle
import com.wmsj.baselibs.base.BaseActivity
import kotlinx.android.synthetic.main.activity_start.*


/**
 * Created by tian
on 2020/1/15.
 */
class StartActivity : BaseActivity(){


    override fun layoutId(): Int {
        return R.layout.activity_start
    }

    override fun initData(savedInstanceState: Bundle?) {
        initWhiteActionBar(-1, null, "开始")
        tv_start_title.text = "11111111111111111"
    }

    override fun initView() {
    }

    override fun start() {
    }


}