package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.ActsPerson

/**
 * Created by tian
on 2019/8/5.
 */
class RecuritMemberContract {

    interface View : IBaseView {

        /**
         * 获取招募人数
         */
        fun setModelsData(data: ActsPerson)

        /**
         * 取消报名
         */
        fun cancelResult(data: String)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 获取招募人数
         */
        fun requestModelsData(type: String, id: String, keyword: String, page: String)

        /**
         * 取消报名
         */
        fun cancelSign(id: String, state: String)

        /**
         * 时长补录
         */
        fun timeEnter(map: HashMap<String, String>)

    }
}