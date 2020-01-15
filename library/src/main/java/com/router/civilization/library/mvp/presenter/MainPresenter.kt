package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle
import com.router.civilization.library.mvp.contract.MainContract
import com.router.civilization.library.mvp.model.MainModel
import org.json.JSONObject

/**
 * Created by tian
on 2019/7/12.
 */
class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {


    private val model by lazy { MainModel() }

    override fun getClientsAuth() {
        checkViewAttached()
        mRootView?.apply { showLoading() }
        addSubscription(disposable = model.getClientsAuth()
                .subscribe({ response ->
                    mRootView?.apply {
                        if (response.state == "1") {
                            val jsonObject = JSONObject(response.data.toString())
                            setClientsAuth(jsonObject.get("auth").toString())
                        } else {
                            //处理异常
                            showError(response.message!!, 0)
                        }

                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable)!!, ExceptionHandle.errorCode)
                    }
                }))
    }

    override fun getBaseInfo(type: String) {
        checkViewAttached()
        addSubscription(disposable = model.getBaseInfo(type)
                .subscribe({ response ->
                    mRootView?.apply {
                        if (response.state == "1") {
                            getBaseInfoResult(response.data, type)
                        } else {
                            //处理异常
                            showError(response.message!!, 0)
                        }
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }


    override fun getVersion() {
        checkViewAttached()
        addSubscription(disposable = model.getVersion()
                .subscribe({ response ->
                    mRootView?.apply {
                        if (response.state == "1") {
                            setVersionResult(response.data)
                        } else {
                            //处理异常
//                            showError(response.message!!, 0)
                        }
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
//                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

}