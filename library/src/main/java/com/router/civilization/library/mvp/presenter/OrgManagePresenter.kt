package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.mvp.contract.OrgManageContract
import com.router.civilization.library.mvp.model.OrgManageModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle

/**
 * Created by tian
on 2019/8/5.
 */
class OrgManagePresenter : BasePresenter<OrgManageContract.View>(), OrgManageContract.Presenter {


    private val model: OrgManageModel by lazy {
        OrgManageModel()
    }


    override fun requestModelsData(type: String) {
        // 检测是否绑定 View
        checkViewAttached()
        var disposable = model.requestHomeData(type)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        setModelsData(response.data)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t)!!, ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)

    }

    override fun hangupOrgOperation(state: String, id: String) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()
        var disposable = model.hangupOrgOperation(state, id)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        if (response.state == "1") {
                            operationResult(response.message)
                        } else {
                            showError(response.message!!, response.httpcode.toInt())
                        }
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t)!!, ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }

    override fun hangupOrgSearch(keyword: String) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()
        var disposable = model.hungupSearch(keyword)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        setModelsData(response.data)
                    }
                }, { t ->
                    mRootView?.apply {
                        dismissLoading()
                        showError(ExceptionHandle.handleException(t)!!, ExceptionHandle.errorCode)
                    }
                })

        addSubscription(disposable)
    }


}