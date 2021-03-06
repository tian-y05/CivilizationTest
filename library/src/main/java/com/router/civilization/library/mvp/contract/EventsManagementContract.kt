package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.EventsBean
import com.router.civilization.library.bean.HomeActivityListBean

/**
 * Created by tian
on 2019/8/5.
 */
class EventsManagementContract {

    interface View : IBaseView {

        /**
         * 首页活动
         */
        fun setModelsData(data: List<HomeActivityListBean>)

        /**
         * 组织活动管理
         */
        fun setOrgActs(data: List<EventsBean>)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

        /**
         * 撤回草稿
         */
        fun backDraftResult(msg: String)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 获取首页活动
         */
        fun requestModelsData(type: String, num: String, name: String)

        /**
         * 获取组织活动管理
         */
        fun requestOrgActs(type: String, num: String)

        /**
         * 撤回草稿
         */
        fun backDraft(map: HashMap<String, String>)

        /**
         * 活动发布 删除
         */
        fun actsManage(map: HashMap<String, String>)
    }
}