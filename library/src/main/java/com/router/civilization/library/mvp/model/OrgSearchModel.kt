package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitHomeManager
import com.router.civilization.library.bean.ArticileOrgList
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class OrgSearchModel {
    /**
     * 查找组织
     */
    fun requestHomeData(key: String, page: String): Observable<BaseResponse<List<ArticileOrgList>>> {
        var map = HashMap<String, String>()
        map.put("keyword", key)
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitHomeManager.service.searchOrgList(map)
                .compose(SchedulerUtils.ioToMain())
    }

}