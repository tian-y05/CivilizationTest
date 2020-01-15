package com.router.civilization.library.mvp.presenter

import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle
import com.router.civilization.library.mvp.contract.StationSpotContract
import com.router.civilization.library.mvp.model.StationSpotModel

/**
 * Created by tian
on 2019/8/5.
 */
class StationSpotPresenter : BasePresenter<StationSpotContract.View>(), StationSpotContract.Presenter {


    private val stationModel: StationSpotModel by lazy {
        StationSpotModel()
    }


    override fun requestModelsData(station_id: String, num: String) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()
        var disposable = stationModel.requestHomeData(station_id, num)
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