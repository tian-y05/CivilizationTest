package com.router.civilization.library.mvp.contract


import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.HangupDetailBean
import com.router.civilization.library.bean.OrgBaseInfoBean
import com.router.civilization.library.bean.OrgDetailBean

/**
 * Created by tian
on 2019/7/12.
 */
class OrgDetailsContract {

    interface View : IBaseView {

        /**
         * 组织详情
         */
        fun setOrgDetails(data: OrgDetailBean)

        /**
         * 组织详情
         */
        fun setOrgInfoDetails(data: OrgBaseInfoBean)

        /**
         * 加入组织
         */
        fun joinOrgResult(data: String)

        /**
         * 退出组织
         */
        fun exitOrgResult(data: String)

        /**
         * 挂靠组织详情
         */
        fun orgManageDetail(data: HangupDetailBean)

        /**
         * 操作结果
         */
        fun operationResult(data: String)

        /**
         * 失败
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 首页的组织详情
         */
        fun getOrgDetails(id: String)

        /**
         * 我的组织详情
         */
        fun getMyOrgDetails(id: String)

        /**
         * 加入组织
         */
        fun joinOrg(id: String)

        /**
         * 挂靠组织
         */
        fun applyOrg(state: String, id: String)

        /**
         * 退出组织
         */
        fun exitOrg(id: String)

        /**
         * 挂靠组织详情
         */
        fun orgManageDetail(id: String)

        /**
         * 挂靠组织操作
         */
        fun hangupOrgOperation(state: String, id: String)

    }
}