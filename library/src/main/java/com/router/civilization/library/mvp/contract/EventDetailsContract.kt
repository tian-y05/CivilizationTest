package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.ActivityShowBean


/**
 * Created by tian
on 2019/7/12.
 */
class EventDetailsContract {

    interface View : IBaseView {

        /**
         * 活动详情
         */
        fun setEventDetails(data: ActivityShowBean)

        /**
         * 活动报名
         */
        fun joinResult(data: String)

        /**
         * 失败
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 活动详情
         */
        fun getEventDetails(id: String)

        /**
         * 活动报名
         */
        fun joinActivity(aid: String)


    }
}