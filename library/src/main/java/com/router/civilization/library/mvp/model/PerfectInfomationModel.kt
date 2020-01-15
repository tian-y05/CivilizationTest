package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitHomeManager
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.bean.AreaBean
import com.router.civilization.library.bean.UserInfo
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by tian
on 2019/7/12.
 */
class PerfectInfomationModel {


    /**
     * 获取地区
     */
    fun getArea(area: String): Observable<BaseResponse<List<AreaBean>>> {
        val map = HashMap<String, String>()
        map.put("id", area)
        return RetrofitHomeManager.service.getArea(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 完善信息
     */
    fun doPerfectInfo(map: HashMap<String, String>): Observable<BaseResponse<Any>> {
        return RetrofitManager.service.completeBaseInfo(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 志愿者信息
     */
    fun getUserInfo(uid: String): Observable<BaseResponse<UserInfo>> {
        val map = HashMap<String, String>()
        map.put("userid", uid)
        return RetrofitManager.service.getUserInfo(map)
                .compose(SchedulerUtils.ioToMain())
    }
}