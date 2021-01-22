package com.example.commonmodule.utils

import android.app.Activity
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

/**
 * Activity管理
 */
class RunningActivityManager private constructor() {
    private val activityList: ArrayList<Activity>?
    val size: Int
        get() = activityList!!.size

    /**
     * 清除保存的Activity队列
     */
    @Synchronized
    fun clear() {
        activityList?.clear()
        mRunningActivityManage = null
    }

    /**
     * 结束队列中的全部Activity
     */
    @Synchronized
    fun finishAllActivity() {
        if (activityList != null) {
            Log.i(
                TAG,
                "activityList.size(): " + activityList.size
            )
            val iterator = activityList.iterator()
            while (iterator.hasNext()) {
                val activity = iterator.next()
                if (activity.isFinishing) {
                    iterator.remove()
                    Log.i(
                        TAG,
                        "activity: $activity"
                    )
                } else {
                    iterator.remove()
                    activity.finish()
                    Log.i(
                        TAG,
                        "the activity is close: $activity"
                    )
                }
            }
        }
    }

    /**
     * 除了主页其它页面都关闭，用于推送跳转
     */
    @Synchronized
    fun finishAllActivityExceptMainActivity() {
        if (activityList != null) {
            val iterator = activityList.iterator()
            while (iterator.hasNext()) {
                val activity = iterator.next()
                if (activity.isFinishing) {
                    iterator.remove()
                    Log.i(
                        TAG,
                        "activity: $activity"
                    )
                } else {
                    iterator.remove()
                    activity.finish()
                    Log.i(
                        TAG,
                        "the activity is close: $activity"
                    )
                }
            }
        }
    }

    /**
     * 结束掉其他Activity
     *
     * @param exceptActivity 除了该Activity外都移除
     */
    @Synchronized
    fun finishOthersActivity(exceptActivity: Activity) {
        if (activityList != null) {
            Log.i(
                TAG,
                "activityList.size(): " + activityList.size
            )

            val iterator = activityList.iterator()
            while (iterator.hasNext()) {
                val activity = iterator.next()
                if (activity === exceptActivity) {
                    continue
                }
                if (activity.isFinishing) {
                    iterator.remove()
                    Log.i(
                        TAG,
                        "activity: $activity"
                    )
                } else {
                    iterator.remove()
                    activity.finish()
                    Log.i(
                        TAG,
                        "the activity is close: $activity"
                    )
                }
            }
        }
    }

    /**
     * 添加Activity到列表
     *
     * @param activity
     */
    @Synchronized
    fun addActivity(activity: Activity) {
        if (!activityList!!.contains(activity)) {
            activityList!!.add(activity)
        }
        Log.i(
            TAG,
            "add class name: " + activity.javaClass.name + ", size: " + activityList.size
        )
    }

    /**
     * 移除指定的Activity
     *
     * @param activity
     */
    @Synchronized
    fun removeActivity(activity: Activity) {
        if (activityList != null && activityList.size > 0) {
            val size = activityList.size
            for (index in size - 1 downTo 0) {
                val item = activityList[index]
                if (item.javaClass.name
                        .equals(activity.javaClass.name, ignoreCase = true)
                ) {
                    activityList.removeAt(index)
                    Log.i(
                        TAG,
                        "remove class name: " + activity.javaClass.name + ", size: " + activityList.size
                    )
                    break
                }
            }
        }
    }

    /**
     * 移除顶部的Activity
     */
    @Synchronized
    fun removeTopActivity() {
        if (activityList != null && activityList.size > 0) {
            Log.i(TAG, "size: " + activityList.size)
            activityList.removeAt(activityList.size - 1)
        }
    }

    /**
     * 栈里是否包含Activity
     */
    @Synchronized
    fun containActivity(activity:Activity):Boolean {
        if (activityList != null && activityList.size > 0) {
            Log.i(TAG, "size: " + activityList.size)
            return activityList.contains(activity)
        }
        return false
    }

    /**
     * 栈里是否包含Activity
     */
    @Synchronized
    fun containActivity(activityName:String):Boolean {
        if (activityList != null && activityList.size > 0) {
            activityList.forEach {
                if (it.localClassName.contains(activityName)){
                    return true
                }
            }
        }
        return false
    }

    fun getActivityList():ArrayList<Activity>?{
        return activityList
    }

    /**
     * 获取顶部的Activity
     */
    @get:Synchronized
    val topActivity: Activity?
        get() {
            var topActivity: Activity? = null
            if (activityList != null && activityList.size > 0) {
                topActivity = activityList[activityList.size - 1]
            }
            return topActivity
        }

    companion object {
        val TAG = RunningActivityManager::class.java.simpleName
        private var mRunningActivityManage: RunningActivityManager? = null

        /**
         * 获取RunningActivityManage的实例
         */
        val instance: RunningActivityManager?
            get() {
                if (null == mRunningActivityManage) {
                    mRunningActivityManage = RunningActivityManager()
                }
                return mRunningActivityManage
            }
    }

    /**
     * 私有化构造方法
     */
    init {
        activityList = ArrayList()
    }
}