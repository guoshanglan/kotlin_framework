package com.example.basemodule.application

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.example.commonmodule.preference.PreferencesUtils
import com.example.basemodule.callback.LifeCycleCallBack
import com.example.commonmodule.utils.DensityUtils

open class BaseApplication: MultiDexApplication() {

    var debug:Boolean=true;
    override fun onCreate() {
        super.onCreate()
        debug = BuildConfig.DEBUG;
        initArouter()
        registerActivityLifecycleCallbacks(LifeCycleCallBack())
        DensityUtils.setDensity(this, 480f)
        PreferencesUtils.instance.init(this)

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