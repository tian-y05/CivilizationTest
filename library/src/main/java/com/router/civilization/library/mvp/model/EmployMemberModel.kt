package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.bean.OrgMemberBean
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class EmployMemberModel {

    /**
     * 组织成员
     */
    fun requestHomeData(map: HashMap<String, String>): Observable<BaseResponse<List<OrgMemberBean>>> {
        return RetrofitManager.service.orgMember(map)
                .compose(SchedulerUtils.ioToMain())
    }


    /**
     * 组织录用
     */
    fun orgEmployment(map: HashMap<String, String>): Observable<BaseResponse<Any>> {
        return RetrofitManager.service.orgEmployment(map)
                .compose(SchedulerUtils.ioToMain())
    }
}