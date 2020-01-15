package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.OrgBaseInfoBean

import com.router.civilization.library.bean.UserCentreBean

/**
 * Created by tian
on 2019/7/12.
 */
class VolunteerContract {

    interface View : IBaseView {

        /**
         * 志愿者信息
         */
        fun userCentreResult(data: UserCentreBean)

        /**
         * 组织信息
         */
        fun orgInfoResult(data: OrgBaseInfoBean)

        /**
         * 失败
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 志愿者中心
         */
        fun getUserCentre(uid: String)

        /**
         * 获取当前组织信息
         */
        fun getOrgInfo(uid: String)
    }
}