package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.mvp.contract.OrgSearchContract
import com.router.civilization.library.mvp.model.OrgSearchModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle

/**
 * Created by tian
on 2019/8/5.
 */
class OrgSearchPresenter : BasePresenter<OrgSearchContract.View>(), OrgSearchContract.Presenter {

    private val model: OrgSearchModel by lazy {
        OrgSearchModel()
    }

    override fun requestModelsData(type: String, num: String) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()
        var disposable = model.requestHomeData(type, num)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        if(response.data != null){
                            setModelsData(response.data)
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