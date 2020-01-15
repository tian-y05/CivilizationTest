package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle
import com.router.civilization.library.mvp.contract.PlatformShowContract
import com.router.civilization.library.mvp.model.PlatformShowModel

/**
 * Created by tian
on 2019/8/5.
 */
class PlatformShowPresenter : BasePresenter<PlatformShowContract.View>(), PlatformShowContract.Presenter {


    private val platformModel: PlatformShowModel by lazy {
        PlatformShowModel()
    }


    override fun requestModelsData(type: Int) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()
        var disposable = platformModel.requestColumnData(type)
                .subscribe({ response ->
                    mRootView?.apply {
                        dismissLoading()
                        setModelsData(response.data!!)
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