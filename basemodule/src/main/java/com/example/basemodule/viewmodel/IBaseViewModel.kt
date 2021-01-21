package com.example.basemodule.viewmodel

import android.content.Context
import androidx.lifecycle.Lifecycle

import com.example.basemodule.callback.IBaseView

/**
 * ViewModel接口
 * @author gukaihong
 * @date 2020/4/22
 */
interface IBaseViewModel<V : IBaseView> {
    /**添加生命周期管理*/
    fun attachLifecycle(life: Lifecycle)

    /**添加UI操作*/
    fun attachUI(view: V?)

    /**获取UI操作*/
    val view: V?

    /**是否已添加*/
    val isUIAttached: Boolean

    /**释放*/
    fun detach()

}