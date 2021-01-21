package com.example.commonnetwork

import com.example.commonnetwork.bean.BaseResponse
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class BaseObserver<T> : Observer<BaseResponse<T>> {

    private val TAG = "BaseObserver"
    override fun onNext(response: BaseResponse<T>) {
        if (response.code === "200") {
            onSuccess(response.data)
        } else {
            onFailure(null, response.msg)
        }
    }

    override fun onError(e: Throwable) {
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onComplete() {}

    abstract fun onSuccess(result: T)

    abstract fun onFailure(e: Throwable?, errorMsg: String?)


}