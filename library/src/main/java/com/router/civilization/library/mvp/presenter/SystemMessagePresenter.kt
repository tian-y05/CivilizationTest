package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.mvp.contract.SystemMessageContract
import com.router.civilization.library.mvp.model.SystemMessageModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle

/**
 * Created by tian
on 2019/8/5.
 */
class SystemMessagePresenter : BasePresenter<SystemMessageContract.View>(), SystemMessageContract.Presenter {


    private val model: SystemMessageModel by lazy {
        SystemMessageModel()
    }


    override fun requestModelsData( num: String) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()
        var disposable = model.requestHomeData(num)
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