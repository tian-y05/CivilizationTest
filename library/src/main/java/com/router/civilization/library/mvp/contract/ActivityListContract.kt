package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.HomeActivityListBean
import com.router.civilization.library.bean.OrgCataBean
import com.router.civilization.library.bean.OrgSonList

/**se
 * Created by tian
on 2019/8/5.
 */
class ActivityListContract {

    interface View : IBaseView {

        /**
         * 活动一级列表
         */
        fun setActivityCate(data: List<OrgCataBean>)

        /**
         * 活动二级列表
         */
        fun setActivitySonCate(data: List<OrgSonList>)

        /**
         * 模块数据
         */
        fun setModelsData(data: List<HomeActivityListBean>)


        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 活动一级列表
         */
        fun requestActivityCate()

        /**
         * 活动二级列表
         */
        fun requestActivitySonCate(key: String)

        /**
         * 获取模块数据
         */
        fun requestModelsData(key: String, num: String)

    }
}