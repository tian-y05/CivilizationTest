package com.router.civilization.library.mvp.presenter

import android.content.Context
import com.router.civilization.library.mvp.contract.PerfectOrgInfoContract
import com.router.civilization.library.mvp.model.PerfectOrgInfoModel
import com.router.civilization.library.base.BasePresenter
import com.router.civilization.library.exception.ExceptionHandle
import com.router.civilization.library.scheduler.SchedulerUtils
import io.reactivex.Observable
import top.zibin.luban.Luban
import java.util.*

/**
 * Created by tian
on 2019/7/12.
 */
class PerfectOrgInfoPresenter : BasePresenter<PerfectOrgInfoContract.View>(), PerfectOrgInfoContract.Presenter {

    private var imageLists = ArrayList<String>()

    private val model by lazy { PerfectOrgInfoModel() }


    override fun getArea(id: String, type: String) {
        checkViewAttached()
        addSubscription(disposable = model.getArea(id)
                .subscribe({ response ->
                    mRootView?.apply {
                        if (response.state == "1") {
                            areaResult(response.data, type)
                        } else {
                            //处理异常
                            showError(response.message!!, 0)
                        }
                        dismissLoading()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

    override fun doPerfectInfo(map: HashMap<String, String>) {
        checkViewAttached()
        mRootView?.apply { showLoading() }
        addSubscription(disposable = model.doPerfectInfo(map)
                .subscribe({ response ->
                    mRootView?.apply {
                        if (response.state == "1") {
                            perfectInfoResult(response.message)
                        } else {
                            //处理异常
                            showError(response.message!!, 0)
                        }
                        dismissLoading()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }


    override fun getOrg(id: String) {
        checkViewAttached()
        addSubscription(disposable = model.getOrg(id)
                .subscribe({ response ->
                    mRootView?.apply {
                        if (response.state == "1") {
                            getOrgResult(response.data)
                        } else {
                            //处理异常
                            showError(response.message!!, 0)
                        }
                        dismissLoading()
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        dismissLoading()
                        showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
                    }
                }))
    }

    override fun imageUpload(mContext: Context, files: ArrayList<String>, type: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        imageLists.clear()
        var disposable = Observable.fromIterable(files)
                .compose(SchedulerUtils.ioToMain())
                .flatMap { t -> model.imageUpload(Luban.with(mContext).load(t).ignoreBy(100).get()[0].absolutePath) }
                .subscribe({ disposable ->
                    mRootView?.apply {
                        if (disposable.state == "1") {
                            imageLists.add(disposable.data.file)
                        } else {
                            showError(disposable.message, 0)
                        }
                        dismissLoading()
                    }
                }, { t ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(t)!!, ExceptionHandle.errorCode)
                        dismissLoading()
                    }
                }, {
                    mRootView?.apply {
                        setImageUploadResult(imageLists, type)
                        dismissLoading()
                    }
                })
        addSubscription(disposable)
    }

}