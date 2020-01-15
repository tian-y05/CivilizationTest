package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.BaseApplication
import com.router.civilization.library.bean.ServiceRecordBean
import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.utils.SPUtil
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class ServiceRecordModel {
    /**
     * 获取我的活动
     */
    fun requestHomeData(page: String): Observable<BaseResponse<ServiceRecordBean>> {
        var map = HashMap<String, String>()
        map.put("api-token", SPUtil.get(BaseApplication.appInstance, "userId", "").toString())
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitManager.service.serviceRecordList(map)
                .compose(SchedulerUtils.ioToMain())
    }

}