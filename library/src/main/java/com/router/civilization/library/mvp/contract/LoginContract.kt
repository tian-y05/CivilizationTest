package com.router.civilization.library.mvp.contract


import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.LoginInfoBean

/**
 * Created by tian
on 2019/7/12.
 */
class LoginContract {

    interface View : IBaseView{

        /**
         * 登录成功回调
         */
        fun doLoginResult(data: LoginInfoBean)


        /**
         * 失败
         */
        fun showError(errorMsg: String,errorCode:Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 请求登录
         */
        fun doLogin(username: String,password: String)


        /**
         * 请求志愿组织登录
         */
        fun doOrgLogin(username: String,password: String)
    }
}