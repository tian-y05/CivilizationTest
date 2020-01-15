package com.router.civilization.library.mvp.model

import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.bean.OrgUserInfo
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class OrgUserInfoModel {

    /**
     * 志愿者信息
     */
    fun orgUserInfo(map: HashMap<String, String>): Observable<BaseResponse<OrgUserInfo>> {
        return RetrofitManager.service.orgUserInfo(map)
                .compose(SchedulerUtils.ioToMain())
    }

}