package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.BaseApplication
import com.router.civilization.library.bean.HangupDetailBean
import com.router.civilization.library.bean.OrgBaseInfoBean
import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.utils.SPUtil
import io.reactivex.Observable

/**
 * Created by tian
on 2019/7/12.
 */
class OrgDetailsModel {

    /**
     * 组织详情
     */
    fun getEventDetails(id: String): Observable<BaseResponse<OrgBaseInfoBean>> {
        var map = HashMap<String, String>()
        map.put("orgid", id)
        map.put("role", SPUtil.get(BaseApplication.appInstance, "role", "").toString())
        return RetrofitManager.service.getOrgInfo(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 我的组织详情
     */
    fun getMyOrgtDetails(id: String): Observable<BaseResponse<OrgBaseInfoBean>> {
        var map = HashMap<String, String>()
        map.put("orgid", id)
        return RetrofitManager.service.myOrgInfo(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 申请加入组织
     */
    fun joinOrg(id: String): Observable<BaseResponse<Any>> {
        var map = HashMap<String, String>()
        map.put("userid", SPUtil.get(BaseApplication.appInstance, "userId", "").toString())
        map.put("orgid", id)
        return RetrofitManager.service.joinOrg(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 挂靠组织
     */
    fun applyOrg(state: String, id: String): Observable<BaseResponse<Any>> {
        var map = HashMap<String, String>()
        map.put("state", state)
        map.put("id", id)
        return RetrofitManager.service.applyOrg(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 退出组织
     */
    fun exitOrg(id: String): Observable<BaseResponse<Any>> {
        var map = HashMap<String, String>()
        map.put("orgid", id)
        return RetrofitManager.service.exitOrg(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 挂靠组织详情
     */
    fun orgManageDetail(id: String): Observable<BaseResponse<HangupDetailBean>> {
        var map = HashMap<String, String>()
        map.put("id", id)
        return RetrofitManager.service.hangupOrgDetail(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 组织挂靠操作
     */
    fun hangupOrgOperation(state: String, id: String): Observable<BaseResponse<Any>> {
        var map = HashMap<String, String>()
        map.put("state", state)
        map.put("id", id)
        return RetrofitManager.service.hangupOrgOperation(map)
                .compose(SchedulerUtils.ioToMain())
    }
}