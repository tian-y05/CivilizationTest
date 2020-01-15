package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.mvp.contract.EventDetailsContract
import com.router.civilization.library.mvp.model.EventDetailsModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle

/**
 * Created by tian
on 2019/7/12.
 */
class EventDeatilsPresenter : BasePresenter<EventDetailsContract.View>(), EventDetailsContract.Presenter {


    private val model by lazy { EventDetailsModel() }


    override fun getEventDetails(id: String) {
        checkViewAttached()
        mRootView?.apply { showLoading() }
        addSubscription(disposable = model.getEventDetails(id)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        setEventDetails(response.data)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

    override fun joinActivity(aid: String) {
        checkViewAttached()
        mRootView?.apply { showLoading() }
        addSubscription(disposable = model.joinActivity(aid)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        if (response.state == "1") {
                            joinResult(response.message)
                        } else {
                            showError(response.message!!, response.httpcode.toInt())
                        }
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }
}