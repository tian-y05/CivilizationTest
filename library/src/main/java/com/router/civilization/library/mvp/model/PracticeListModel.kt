package com.router.civilization.library.mvp.model

import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitHomeManager
import com.router.civilization.library.bean.PracticeBaseBean
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class PracticeListModel {
    /**
     * 获取实践基地
     */
    fun requestHomeData(page: String): Observable<BaseResponse<List<PracticeBaseBean>>> {
        var map = HashMap<String, String>()
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitHomeManager.service.getPracticeLists(map)
                .compose(SchedulerUtils.ioToMain())
    }

}