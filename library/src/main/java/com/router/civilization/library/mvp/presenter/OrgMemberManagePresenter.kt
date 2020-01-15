package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.mvp.contract.OrgMemberContract
import com.router.civilization.library.mvp.model.OrgMemberManageModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle

/**
 * Created by tian
on 2019/8/5.
 */
class OrgMemberManagePresenter : BasePresenter<OrgMemberContract.View>(), OrgMemberContract.Presenter {


    private val model: OrgMemberManageModel by lazy {
        OrgMemberManageModel()
    }

    override fun orgMemberManage(state: String, num: String) {
        checkViewAttached()
        var disposable = model.orgMemberManage(state, num)
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

    override fun orgMemberCheck(type: String, id: String) {
        checkViewAttached()
        mRootView?.showLoading()
        var disposable = model.orgMemberCheck(type, id)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        if (response.state == "1") {
                            orgCheckResult(response.message)
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


}