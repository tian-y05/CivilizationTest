package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.OrgMemberBean

/**
 * Created by tian
on 2019/8/5.
 */
class EmployMemberContract {

    interface View : IBaseView {

        /**
         * 组织成员
         */
        fun setModelsData(data: List<OrgMemberBean>)

        /**
         * 录用结果
         */
        fun employmentResult(data: String)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 组织成员
         */
        fun requestModelsData(map: HashMap<String, String>)

        /**
         * 组织录用
         */
        fun orgEmployment(map: HashMap<String, String>)

    }
}