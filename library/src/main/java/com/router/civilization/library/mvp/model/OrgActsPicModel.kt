package com.router.civilization.library.mvp.model

import com.router.civilization.library.Const
import com.router.civilization.library.retrofit.BaseResponse
import com.router.civilization.library.retrofit.RetrofitManager
import com.router.civilization.library.retrofit.RetrofitResourceManager
import com.router.civilization.library.bean.ImageBean
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by tian
on 2019/8/5.
 */
class OrgActsPicModel {

    /**
     * 图片上传
     */
    fun imageUpload(file: String): Observable<BaseResponse<ImageBean>> {
        var requestFile = RequestBody.create(MediaType.parse("image/*"), File(file))
        var part = MultipartBody.Part.createFormData("file", file, requestFile)
        return RetrofitResourceManager(Const.UPLOADURL).service.postImage(part)
                .compose(SchedulerUtils.ioToMain())
    }


    /**
     * 活动图片上传
     */
    fun pulishData(map: Map<String, String>): Observable<BaseResponse<Any>> {
        return RetrofitManager.service.actsPicUpload(map)
                .compose(SchedulerUtils.ioToMain())
    }

}