package com.router.civilization.library.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.router.civilization.library.Const
import com.router.civilization.library.R
import com.router.civilization.library.mvp.contract.LoginContract
import com.router.civilization.library.mvp.presenter.LoginPresenter
import com.router.civilization.library.ui.activity.FindIdentityActivity
import com.router.civilization.library.ui.activity.ForgetPasswordActivity
import com.router.civilization.library.ui.activity.PerfectInformationActivity
import com.router.civilization.library.ui.activity.RegisterActivity
import com.router.civilization.library.base.BaseFragment
import com.router.civilization.library.bean.LoginInfoBean
import com.router.civilization.library.eventbus.EventBusMessage
import com.router.civilization.library.eventbus.EventBusUtils
import com.router.civilization.library.utils.IntentUtils
import com.router.civilization.library.utils.SPUtil
import com.router.civilization.library.utils.StringUtils
import com.router.civilization.library.utils.ToastUtils
import com.router.civilization.library.view.SetSuccessDialog
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * 志愿者登录
on 2019/8/16.
 */
class LoginFragment : BaseFragment(), LoginContract.View, View.OnClickListener {

    private var isShow = false
    private val mPresenter by lazy { LoginPresenter() }

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): LoginFragment {
            val fragment = LoginFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initView() {
        mPresenter.attachView(this)
//        initWhiteActionBar(-1, null, mTitle)
        tv_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
        tv_forget.setOnClickListener(this)
        tv_find.setOnClickListener(this)

    }

    override fun lazyLoad() {
    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun doLoginResult(data: LoginInfoBean) {
        SPUtil.put(activity, "userId", data.userid)
        if (data.completion != 1) {
            activity?.let { IntentUtils.to(it, PerfectInformationActivity::class.java) }
        } else {
            ToastUtils.showShort(activity, "登录成功")
            SPUtil.put(activity, "isVolLogin", true)
            SPUtil.put(activity, "isOrgLogin", false)
            SPUtil.put(activity, "role", "volunteer")
            SPUtil.put(activity, "username", et_tel.text.toString())
            SPUtil.put(activity, "password", et_password.text.toString())
            SPUtil.put(activity, "completion", data.completion)
            EventBusUtils.post(EventBusMessage(Const.LOGIN_VOL_SUCCESS, mTitle))
        }


    }

    override fun showError(errorMsg: String, errorCode: Int) {
        ToastUtils.showShort(activity, errorMsg)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_login -> {
                if (StringUtils.isEmpty(et_tel.text.toString())) {
                    ToastUtils.showShort(activity, getString(R.string.account_null))
                    return
                }
                if (StringUtils.isEmpty(et_password.text.toString())) {
                    ToastUtils.showShort(activity, getString(R.string.password_null))
                    return
                }
                if (SPUtil.get(activity, "isOrgLogin", false) as Boolean) {
                    activity?.let {
                        SetSuccessDialog.Builder(it)
                                .setTitle(getString(R.string.tips))
                                .setMessage(getString(R.string.login_vol))
                                .setPositiveButton(getString(R.string.cancel), DialogInterface.OnClickListener { p0, p1 ->
                                    p0.dismiss()
                                })
                                .setNegativeButton(getString(R.string.sure), DialogInterface.OnClickListener { p0, p1 ->
                                    p0.dismiss()
                                    mPresenter.doLogin(et_tel.text.toString(), et_password.text.toString())
                                })
                                .setWith(0.77f)
                                .create()
                                .show()
                    }
                } else {
                    mPresenter.doLogin(et_tel.text.toString(), et_password.text.toString())
                }
            }
            R.id.tv_register -> {
                var bundle = Bundle()
                bundle.putString("role", "zyz")
                activity?.let { IntentUtils.to(it, RegisterActivity::class.java, bundle) }
            }
            R.id.tv_forget -> {
                var bundle = Bundle()
                bundle.putBoolean("isEdit", false)
                bundle.putString("role", "zyz")
                activity?.let { IntentUtils.to(it, ForgetPasswordActivity::class.java, bundle) }
            }
            R.id.tv_find -> {
                activity?.let { IntentUtils.to(it, FindIdentityActivity::class.java) }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }


    override fun onReceiveEvent(event: EventBusMessage<Any>) {
        when (event.code) {
            Const.PERFECT_INFO_SUCCESS -> {
                if (!StringUtils.isEmpty(et_tel.text.toString()) && !StringUtils.isEmpty(et_password.text.toString())) {
                    mPresenter.doLogin(et_tel.text.toString(), et_password.text.toString())
                }
            }
        }
    }
}