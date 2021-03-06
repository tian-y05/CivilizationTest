package com.router.civilization.library.ui.activity

import android.os.Bundle
import android.view.View
import com.router.civilization.library.Const
import com.router.civilization.library.R
import com.router.civilization.library.base.BaseActivity
import com.router.civilization.library.eventbus.EventBusMessage
import com.router.civilization.library.utils.IntentUtils
import kotlinx.android.synthetic.main.activity_account_security.*

/**
 * 账号安全
on 2019/8/26.
 */
class AccountSecurityActivity : BaseActivity() {

    private var role = ""

    override fun layoutId(): Int {
        return R.layout.activity_account_security
    }

    override fun initData(savedInstanceState: Bundle?) {
        role = intent.getStringExtra("role")
    }

    override fun initView() {
        initWhiteActionBar(View.VISIBLE, View.OnClickListener {
            finish()
        }, getString(R.string.account_security))
        rl_tel.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("role", role)
            IntentUtils.to(this, BindTelActivity::class.java,bundle)
        }
        rl_pwd.setOnClickListener {
            var bundle = Bundle()
            bundle.putBoolean("isEdit", true)
            bundle.putString("role", role)
            IntentUtils.to(this, ForgetPasswordActivity::class.java, bundle)
        }
    }

    override fun start() {
    }

    override fun onReceiveEvent(event: EventBusMessage<Any>) {
        when (event.code) {
            Const.VOL_EDIT_PWD, Const.VOL_EDIT_TEL -> {
                finish()
            }
        }
    }
}