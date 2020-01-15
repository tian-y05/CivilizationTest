package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.BaseApplication
import com.router.civilization.library.bean.ActivityService
import com.router.civilization.library.bean.ClockCardBean
import com.router.civilization.library.bean.MyActivityBean
import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.utils.SPUtil
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class MyActsModel {
    /**
     * 获取我的活动
     */
    fun requestHomeData(type: String, page: String): Observable<BaseResponse<List<MyActivityBean>>> {
        var map = HashMap<String, String>()
        map.put("userid", SPUtil.get(BaseApplication.appInstance, "userId", "").toString())
        map.put("status", type)
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitManager.service.getMyActivity(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 签到
     */
    fun checkCard(map: HashMap<String, String>): Observable<BaseResponse<ClockCardBean>> {
        return RetrofitManager.service.checkCard(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 志愿者取消报名
     */
    fun removeSign(map: HashMap<String, String>): Observable<BaseResponse<Any>> {
        return RetrofitManager.service.removeSign(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 服务详情
     */
    fun getActivityService(map: HashMap<String, String>): Observable<BaseResponse<ActivityService>> {
        return RetrofitManager.service.getActivityService(map)
                .compose(SchedulerUtils.ioToMain())
    }
}