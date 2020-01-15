package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.OrgUserInfo


/**
 * Created by tian
on 2019/7/12.
 */
class OrgUserInfoContract {

    interface View : IBaseView {

        /**
         * 志愿者信息
         */
        fun userInfoResult(data: OrgUserInfo)


        /**
         * 失败
         */
        fun showError(errorMsg: String, errorCode: Int)
    }

    interface Presenter : IPresenter<View> {

        /**
         * 志愿者信息
         */
        fun orgUserInfo(map: HashMap<String, String>)


    }
}