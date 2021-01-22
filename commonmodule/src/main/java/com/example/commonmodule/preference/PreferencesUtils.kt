package com.example.commonmodule.preference

import android.content.Context


/**
 * 数据存储类
 * @author gukaihong
 * @date 2020/4/22
 */

class PreferencesUtils private constructor() {
    lateinit var sp: PreferencesImp


    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = PreferencesUtils()
    }



    fun init(context: Context) {
        PreferencesImp.instance.init(context)
        sp = PreferencesImp.instance
    }




}