package com.router.civilization.library.mvp.model

import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitHomeManager
import com.router.civilization.library.bean.StationListBean
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class StationSpotModel {
    /**
     * 获取所站风貌实践站数据
     */
    fun requestHomeData(station_id: String, page: String): Observable<BaseResponse<List<StationListBean>>> {
        var map = HashMap<String, String>()
        map.put("station_id", station_id)
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitHomeManager.service.getStationSpotLists(map)
                .compose(SchedulerUtils.ioToMain())
    }

}