package com.example.basemodule.viewmodel

import com.example.basemodule.callback.IBaseView

/**
 * ViewModel基类
 *
 * @author gukaihong
 * @date 2020/4/24
 */
open class BaseViewModel : SuperViewModel<IBaseView>(){
    /**刷新*/
    open fun refresh() {}

    /**加载*/
    open fun load() {}



}
