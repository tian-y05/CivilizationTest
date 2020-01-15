package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter
import com.router.civilization.library.bean.ActivityService
import com.router.civilization.library.bean.ClockCardBean
import com.router.civilization.library.bean.MyActivityBean

/**
 * Created by tian
on 2019/8/5.
 */
class MyActsContract {

    interface View : IBaseView {

        /**
         * 模块数据
         */
        fun setModelsData(data: List<MyActivityBean>)

        /**
         * 服务详情
         */
        fun activityServiceDetail(data: ActivityService)


        /**
         * 签到
         */
        fun checkCardResult(date: ClockCardBean)

        /**
         * 取消报名
         */
        fun removeSignResult(date: String)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 获取模块数据
         */
        fun requestModelsData(type: String, num: String)


        /**
         * 服务详情
         */
        fun getActivityService(map: HashMap<String, String>)

        /**
         * 签到
         */
        fun checkCard(map: HashMap<String, String>)

        /**
         * 志愿者取消报名
         */
        fun removeSign(map: HashMap<String, String>)
    }
}