package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.OrgMemberManageBean

/**se
 * Created by tian
on 2019/8/5.
 */
class OrgMemberContract {

    interface View : IBaseView {


        /**
         * 我的组织列表
         */
        fun setModelsData(data: List<OrgMemberManageBean>)

        /**
         * 加入审核移除组织
         */
        fun orgCheckResult(data: String)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 组织成员管理
         */
        fun orgMemberManage(state: String, num: String)

        /**
         * 加入审核移除组织
         */
        fun orgMemberCheck(type: String, id: String)
    }
}