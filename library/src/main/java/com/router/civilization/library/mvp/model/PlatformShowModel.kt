package com.router.civilization.library.mvp.model

import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitHomeManager
import com.router.civilization.library.bean.HomeColumnsBean
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class PlatformShowModel {
    /**
     * 获取组织活动数据
     */
    fun requestColumnData(type: Int): Observable<BaseResponse<List<HomeColumnsBean>>> {
        var map = HashMap<String, Int>()
        map.put("type", type)
        return RetrofitHomeManager.service.getHomeColumns(map)
                .compose(SchedulerUtils.ioToMain())
    }

}