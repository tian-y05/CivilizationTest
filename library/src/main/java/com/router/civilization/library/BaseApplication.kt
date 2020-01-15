package com.router.civilization.library

import android.content.Context
import com.wmsj.baselibs.utils.DisplayManager

/**
 * Created by tian
on 2020/1/15.
 */
object BaseApplication {
    var appInstance: Context? = null
        private set

    fun init(context: Context) {
        appInstance = context
        DisplayManager.init(context)
    }


}