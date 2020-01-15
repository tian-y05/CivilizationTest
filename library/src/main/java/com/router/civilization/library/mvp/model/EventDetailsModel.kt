package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.BaseApplication
import com.router.civilization.library.bean.ActivityShowBean
import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.utils.SPUtil
import io.reactivex.Observable

/**
 * Created by tian
on 2019/7/12.
 */
class EventDetailsModel {

    /**
     * 活动详情
     */
    fun getEventDetails(id: String): Observable<BaseResponse<ActivityShowBean>> {
        var map = HashMap<String, String>()
        map.put("id", id)
        return RetrofitManager.service.getActivityShow(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 活动报名
     */
    fun joinActivity(aid: String): Observable<BaseResponse<Any>> {
        var map = HashMap<String, String>()
        map.put("userid", SPUtil.get(BaseApplication.appInstance, "userId", "").toString())
        map.put("aid", aid)
        return RetrofitManager.service.joinActivity(map)
                .compose(SchedulerUtils.ioToMain())
    }
}