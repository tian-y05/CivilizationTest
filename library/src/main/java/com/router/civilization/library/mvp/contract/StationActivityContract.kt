package com.router.civilization.library.mvp.contract

import com.router.civilization.library.bean.HomeActivityListBean
import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter


/**
 * 所站风貌相关活动
on 2019/8/5.
 */
class StationActivityContract {

    interface View : IBaseView {

        /**
         * 模块数据
         */
        fun setModelsData(data: List<HomeActivityListBean>)


        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 获取模块数据
         */
        fun requestModelsData(station_id: String, state: String, num: String, type: String)


    }
}