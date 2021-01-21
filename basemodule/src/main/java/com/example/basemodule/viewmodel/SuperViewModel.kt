package com.example.basemodule.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

import com.example.basemodule.callback.IBaseView

/**
 * ViewModel层
 * 主要用于关联视图层。
 * @author gukaihong
 * @date 2020/4/22
 */
open class   SuperViewModel<V : IBaseView> : LifecycleOwner, LifecycleObserver, IBaseViewModel<V>{

    private var mUIRef: V? = null
    private var mLifeRef: Lifecycle? = null

    override fun attachUI(view: V?) {
        mUIRef = view
    }

    override fun getLifecycle(): Lifecycle {
        return mLifeRef!!
    }

    override fun attachLifecycle(life: Lifecycle) {
        mLifeRef = life
    }

    override val view: V
        get() = mUIRef!!

    override val isUIAttached: Boolean
        get() = mUIRef != null

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun detach() {

        mUIRef = null
        mLifeRef = null
        onDestroy()
    }

    open fun onDestroy() {

    }

}
