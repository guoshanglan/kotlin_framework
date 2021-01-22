package com.example.basemodule.callback

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.example.commonmodule.utils.RunningActivityManager

/**
 * @Description:    activity管理从baseActivity单独移出来处理   记录当前app是否显示
 * @Author:         zhh
 * @CreateDate:     2020/4/29 19:44
 */
class LifeCycleCallBack : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        RunningActivityManager.instance?.addActivity(activity)

    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        RunningActivityManager.instance?.removeActivity(activity)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }


    override fun onActivityStopped(activity: Activity) {
    }
}