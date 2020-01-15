package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.bean.OrgMemberManageBean
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class OrgMemberManageModel {


    /**
     * 组织成员管理列表
     */
    fun orgMemberManage(type: String, page: String): Observable<BaseResponse<List<OrgMemberManageBean>>> {
        var map = HashMap<String, String>()
        map.put("type", type)
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitManager.service.orgManageMember(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加入审核移除组织
     */
    fun orgMemberCheck(type: String, id: String): Observable<BaseResponse<Any>> {
        var map = HashMap<String, String>()
        map.put("type", type)
        map.put("id", id)
        return RetrofitManager.service.orgMemberCheck(map)
                .compose(SchedulerUtils.ioToMain())
    }

}