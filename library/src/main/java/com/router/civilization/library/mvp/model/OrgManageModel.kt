package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.bean.HangupOrgBean
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class OrgManageModel {
    /**
     * 组织挂靠
     */
    fun requestHomeData(type: String): Observable<BaseResponse<List<HangupOrgBean>>> {
        var map = HashMap<String, String>()
        map.put("type", type)
        return RetrofitManager.service.hangupOrgList(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 组织挂靠搜索
     */
    fun hungupSearch(keyword: String): Observable<BaseResponse<List<HangupOrgBean>>> {
        var map = HashMap<String, String>()
        map.put("keyword", keyword)
        return RetrofitManager.service.hangupOrgSearch(map)
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