package com.example.basemodule.application

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter

class BaseApplication: MultiDexApplication() {

    var debug:Boolean=true;
    override fun onCreate() {
        super.onCreate()
        debug = BuildConfig.DEBUG;
        initArouter()

    }



    /**加载阿里路由*/
    fun initArouter() {
        if (debug) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }



}