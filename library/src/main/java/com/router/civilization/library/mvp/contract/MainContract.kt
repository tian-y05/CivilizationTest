package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.BaseInfoBean
import com.router.civilization.library.bean.VersionBean

/**
 * Created by tian
on 2019/7/12.
 */
class MainContract {

    interface View : IBaseView {

        /**
         * 客户端应用权限
         */
        fun setClientsAuth(string: String)

        /**
         *  获取基本信息
         */
        fun getBaseInfoResult(data: List<BaseInfoBean>, type:String)

        /**
         *  获取版本信息
         */
        fun setVersionResult(data: VersionBean)

        /**
         * 失败
         */
        fun showError(errorMsg: String,errorCode:Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 客户端应用权限
         */
        fun getClientsAuth()

        /**
         * 获取基本信息
         */
        fun getBaseInfo(type: String)

        /**
         * 获取版本信息
         */
        fun getVersion()
    }
}