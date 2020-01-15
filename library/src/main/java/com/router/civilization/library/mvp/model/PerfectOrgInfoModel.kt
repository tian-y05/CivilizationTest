package com.router.civilization.library.mvp.model

import com.router.civilization.library.Const
import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitHomeManager
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.retrofit.RetrofitResourceManager
import com.router.civilization.library.bean.AreaBean
import com.router.civilization.library.bean.ImageBean
import com.router.civilization.library.bean.OrgListBean
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by tian
on 2019/7/12.
 */
class PerfectOrgInfoModel {


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
     * 主管组织
     */
    fun getOrg(region_id: String): Observable<BaseResponse<List<OrgListBean>>> {
        val map = HashMap<String, String>()
        map.put("region_id", region_id)
        return RetrofitHomeManager.service.getOrg(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 完善信息
     */
    fun doPerfectInfo(map: HashMap<String, String>): Observable<BaseResponse<Any>> {
        return RetrofitManager.service.completeOrgInfo(map)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 图片上传
     */
    fun imageUpload(file: String): Observable<BaseResponse<ImageBean>> {
        var requestFile = RequestBody.create(MediaType.parse("image/*"), File(file))
        var part = MultipartBody.Part.createFormData("file", file, requestFile)
        return RetrofitResourceManager(Const.UPLOADURL).service.postImage(part)
                .compose(SchedulerUtils.ioToMain())
    }

}