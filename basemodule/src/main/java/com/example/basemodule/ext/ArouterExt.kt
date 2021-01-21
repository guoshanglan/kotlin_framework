package com.eaglesoul.basemodule.ext

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

import com.example.commonmodule.JumpUtils

/**
 * @Description:    路由跳转
 * @Author:         zhh
 * @CreateDate:     2020/6/8 19:01
 */
class Delay {
    companion object {
        var delayTime = 0L
        fun isGo(): Boolean {
            return if (System.currentTimeMillis() - delayTime < 500) {
                false
            } else {
                delayTime = System.currentTimeMillis()
                true
            }
        }
    }
}



fun Any?.getFragment(url: String): Any? {
    return ARouter.getInstance().build(url).navigation()
}

fun Any?.goWithTitle(url: String, param: String?) {
    if (Delay.isGo()) {
        ARouter.getInstance().build(url).withString(JumpUtils.KEY_TITLE, param).navigation()
    }
}

fun Any?.goWithUrl(url: String, paramUrl: String) {
    if (Delay.isGo()) {
        ARouter.getInstance().build(url).withString(JumpUtils.KEY_URL, paramUrl).navigation()
    }

}

//fun Any?.goWeb(paramUrl: String) {
//    if (Delay.isGo()) {
//        ARouter.getInstance().build(JumpUtils.ACTIVITY_WEB).withString(JumpUtils.KEY_URL, paramUrl)
//            .navigation()
//    }
//}

fun Any?.goWithTitleAndUrl(url: String, param: String, paramUrl: String) {
    if (Delay.isGo()) {
        ARouter.getInstance().build(url).withString(JumpUtils.KEY_TITLE, param)
            .withString(JumpUtils.KEY_URL, paramUrl).navigation()
    }
}

fun Any?.goWithTitleInt(url: String, param: Int) {
    if (Delay.isGo()) {
        ARouter.getInstance().build(url).withInt(JumpUtils.KEY_TITLE, param).navigation()
    }
}






