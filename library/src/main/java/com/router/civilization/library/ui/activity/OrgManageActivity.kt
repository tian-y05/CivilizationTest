package com.router.civilization.library.ui.activity

import android.os.Bundle
import android.view.View
import com.router.civilization.library.R
import com.router.civilization.library.base.BaseActivity
import com.router.civilization.library.utils.IntentUtils
import kotlinx.android.synthetic.main.activity_org_manage.*


/**
 * 组织挂靠
 */
class OrgManageActivity : BaseActivity() {


    override fun layoutId(): Int {
        return R.layout.activity_org_manage
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView() {
        initWhiteActionBar(View.VISIBLE, View.OnClickListener {
            finish()
        }, getString(R.string.manage_org))

        rl_top.setOnClickListener {
            IntentUtils.to(this, OrgTopActivity::class.java)
        }

        rl_down.setOnClickListener {
            IntentUtils.to(this, OrgDownActivity::class.java)
        }
    }

    override fun start() {
    }
}