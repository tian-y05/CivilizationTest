package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.mvp.contract.ServiceRecordContract
import com.router.civilization.library.mvp.model.ServiceRecordModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle

/**
 * Created by tian
on 2019/8/5.
 */
class ServiceRecordPresenter : BasePresenter<ServiceRecordContract.View>(), ServiceRecordContract.Presenter {


    private val model: ServiceRecordModel by lazy {
        ServiceRecordModel()
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