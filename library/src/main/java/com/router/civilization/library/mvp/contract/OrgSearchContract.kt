package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.ArticileOrgList

/**
 * Created by tian
on 2019/8/5.
 */
class OrgSearchContract {

    interface View : IBaseView {

        /**
         * 模块数据
         */
        fun setModelsData(data: List<ArticileOrgList>)


        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 获取模块数据
         */
        fun requestModelsData(key: String, num: String)


    }
}