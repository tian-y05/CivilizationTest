package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitHomeManager
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.bean.EventsBean
import com.router.civilization.library.bean.HomeActivityListBean
import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.utils.StringUtils
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class EventsManagementModel {
    /**
     * 获取首页活动
     */
    fun requestHomeData(type: String, page: String, name: String): Observable<BaseResponse<List<HomeActivityListBean>>> {
        var map = HashMap<String, String>()
        map.put("state", type)
        map.put("page", page)
        map.put("pagesize", "8")
        if (!StringUtils.isEmpty(name)) {
            map.put("name", name)
        }
        return RetrofitHomeManager.service.getHomeNews(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取组织活动管理
     */
    fun requestOrgActs(type: String, page: String): Observable<BaseResponse<List<EventsBean>>> {
        var map = HashMap<String, String>()
        map.put("type", type)
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitManager.service.getEventsData(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 撤回草稿
     */
    fun backDraft(map: HashMap<String, String>): Observable<BaseResponse<Any>> {
        return RetrofitManager.service.backDraft(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 活动发布 删除
     */
    fun actsManage(map: HashMap<String, String>): Observable<BaseResponse<Any>> {
        return RetrofitManager.service.actsManage(map)
                .compose(SchedulerUtils.ioToMain())
    }
}