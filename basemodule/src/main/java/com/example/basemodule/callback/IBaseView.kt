package com.example.basemodule.callback

/**
 * 加载状态回调
 * @author gukaihong
 * @date 2020/4/22
 */
interface IBaseView {
    /**正常显示*/
    fun showContent()

    /**显示loading*/
    fun showLoading()

    /**无数据状态*/
    fun onRefreshEmpty()

    /**数据错误*/
    fun onRefreshFailure(message: String?)

    /**请求超时、或无网络状态*/
    fun onRefreshNoNetwork()

}