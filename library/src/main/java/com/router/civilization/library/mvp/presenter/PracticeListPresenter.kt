package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle
import com.router.civilization.library.mvp.contract.PracticeListContract
import com.router.civilization.library.mvp.model.PracticeListModel

/**
 * Created by tian
on 2019/8/5.
 */
class PracticeListPresenter : BasePresenter<PracticeListContract.View>(), PracticeListContract.Presenter {


    private val practiceModel: PracticeListModel by lazy {
        PracticeListModel()
    }


    override fun requestModelsData(num: String) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()
        var disposable = practiceModel.requestHomeData(num)
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