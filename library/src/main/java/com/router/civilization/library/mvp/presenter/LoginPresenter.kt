package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.mvp.contract.LoginContract
import com.router.civilization.library.mvp.model.LoginModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle

/**
 * Created by tian
on 2019/7/12.
 */
class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {


    private val searchModel by lazy { LoginModel() }


    override fun doLogin(username: String, password: String) {
        checkViewAttached()
        mRootView?.apply {
            showLoading()
        }
        addSubscription(disposable = searchModel.doLogin(username, password)
                .subscribe({ response ->
                    mRootView?.apply {
                        if (response.state == "1") {

                            doLoginResult(response.data)
                        } else {
                            //处理异常
                            showError(response.message!!, 0)
                        }
                        dismissLoading()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

    override fun doOrgLogin(username: String, password: String) {
        checkViewAttached()
        mRootView?.apply {
            showLoading()
        }
        addSubscription(disposable = searchModel.doOrgLogin(username, password)
                .subscribe({ response ->
                    mRootView?.apply {
                        if (response.state == "1") {
                            doLoginResult(response.data)
                        } else {
                            //处理异常
                            showError(response.message!!, 0)
                        }
                        dismissLoading()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        dismissLoading()
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }
}