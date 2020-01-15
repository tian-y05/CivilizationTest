package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.mvp.contract.OrgUserInfoContract
import com.router.civilization.library.mvp.model.OrgUserInfoModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle

/**
 * Created by tian
on 2019/8/5.
 */
class OrgUserInfoPresenter : BasePresenter<OrgUserInfoContract.View>(), OrgUserInfoContract.Presenter {


    private val eventModel: OrgUserInfoModel by lazy {
        OrgUserInfoModel()
    }


    override fun orgUserInfo(map: HashMap<String, String>) {
        checkViewAttached()
        mRootView?.apply {
            showLoading()
        }
        var disposable = eventModel.orgUserInfo(map)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        userInfoResult(response.data!!)
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