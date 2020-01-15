package com.router.civilization.library.ui.activity

import android.os.Bundle
import android.view.View
import com.router.civilization.library.Const
import com.router.civilization.library.R
import com.router.civilization.library.base.BaseActivity
import com.router.civilization.library.eventbus.EventBusMessage
import com.router.civilization.library.utils.IntentUtils
import com.router.civilization.library.utils.SPUtil
import kotlinx.android.synthetic.main.activity_bind_tel.*

/**
 * 已绑手机号
on 2019/8/26.
 */
class BindTelActivity : BaseActivity() {
    private var tel = ""
    private var role = ""

    override fun layoutId(): Int {
        return R.layout.activity_bind_tel
    }

    override fun initData(savedInstanceState: Bundle?) {
        tel = SPUtil.get(this, "username", "").toString()
        tel = tel.replace(tel.substring(3, 7), "****")
        role = intent.getStringExtra("role")
    }

    override fun initView() {
        initWhiteActionBar(View.VISIBLE, View.OnClickListener {
            finish()
        }, getString(R.string.change_tel))
        tv_number.text = tel
        tv_change.setOnClickListener {
            var bundle = Bundle()
            bundle.putString("role", role)
            IntentUtils.to(this, EditTelActivity::class.java, bundle)
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