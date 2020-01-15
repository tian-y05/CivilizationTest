package com.router.civilization.library.mvp.model

import com.router.civilization.library.scheduler.SchedulerUtils
import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitHomeManager
import com.router.civilization.library.bean.ArticleBean
import io.reactivex.Observable

/**
 * Created by tian
on 2019/8/5.
 */
class ArticleListModel {
    /**
     * 获取组织活动数据
     */
    fun requestHomeData(key: String, page: String): Observable<BaseResponse<ArticleBean>> {
        var map = HashMap<String, String>()
        map.put("key", key)
        map.put("page", page)
        map.put("pagesize", "8")
        return RetrofitHomeManager.service.getArticleLists(map)
                .compose(SchedulerUtils.ioToMain())
    }

}