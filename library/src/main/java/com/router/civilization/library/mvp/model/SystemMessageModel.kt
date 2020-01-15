package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.BaseApplication
import com.router.civilization.library.bean.SystemNewsBean
import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.utils.SPUtil
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class SystemMessageModel {
    /**
     * 系统通知
     */
    fun requestHomeData(page: String): Observable<BaseResponse<List<SystemNewsBean>>> {
        var map = HashMap<String, String>()
        map.put("userid", SPUtil.get(BaseApplication.appInstance, "userId", "").toString())
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitManager.service.systemNews(map)
                .compose(SchedulerUtils.ioToMain())
    }

}