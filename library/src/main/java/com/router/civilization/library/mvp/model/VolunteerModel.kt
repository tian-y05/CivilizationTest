package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.bean.OrgBaseInfoBean
import com.router.civilization.library.bean.UserCentreBean
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by tian
on 2019/7/12.
 */
class VolunteerModel {

    /**
     * 志愿者中心
     */
    fun getUserCentre(uid: String): Observable<BaseResponse<UserCentreBean>> {
        var map = HashMap<String, String>()
        map.put("userid", uid)
        return RetrofitManager.service.getUserCentre(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 组织信息
     */
    fun getUserInfo(uid: String): Observable<BaseResponse<OrgBaseInfoBean>> {
        val map = HashMap<String, String>()
        map.put("orgid", uid)
        return RetrofitManager.service.myOrgInfo(map)
                .compose(SchedulerUtils.ioToMain())
    }
}