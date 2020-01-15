package com.router.civilization.library.mvp.contract

import com.router.civilization.library.base.IBaseView
import com.router.civilization.library.base.IPresenter

import com.router.civilization.library.bean.ConventientPhotoBean
import com.router.civilization.library.bean.HomeBanner
import com.router.civilization.library.bean.MyTakePhotoBean

/**se
 * Created by tian
on 2019/8/5.
 */
class ConvenientPhotoContract {

    interface View : IBaseView {

        /**
         * 轮播图数据
         */
        fun setBannerData(data: List<HomeBanner>)

        /**
         * 模块数据
         */
        fun setModelsData(data: List<ConventientPhotoBean>)

        /**
         * 我的随手拍
         */
        fun setMyCulture(data: List<MyTakePhotoBean>)

        /**
         * 删除我的随手拍
         */
        fun deleteCultureResult(data: String)

        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)

    }


    interface Presenter : IPresenter<View> {

        /**
         * 获取轮播图数据
         */
        fun requestBannerData()

        /**
         * 获取模块数据
         */
        fun requestModelsData(map: Map<String, String>)

        /**
         * 获取我的数据
         */
        fun requestMyCulture(map: Map<String, String>)


        /**
         * 删除我的随手拍
         */
        fun deleteMyCulture(map: Map<String, String>)
    }
}