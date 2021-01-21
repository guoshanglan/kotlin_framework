package com.example.commonnetwork

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.commonnetwork.bean.BaseResponse
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File


//网络请求公共类
class RequestUtils {
    companion object {
        protected var mToast: Toast? = null


        /**
         * Get 请求
         * @param lifecycleOwner  生命周期对象
         * @param url 接口地址
         * @param params 请求参数
         * @param observer  接口回调
         */

        @SuppressLint("CheckResult")
         fun get(lifecycleOwner: LifecycleOwner, url: String?, params: Map<String?, Any?>?, observer: MyObserver<BaseResponse<*>?>) {
            RetrofitUtils.getApiUrl()
                ?.getUser(url, params)!!.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<*>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<*>) {
                        observer.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        observer.onFailure(e, RxExceptionUtil.exceptionHandler(e!!))
                    }

                })
        }





        /**
         * post 请求
         * @param lifecycleOwner  生命周期对象
         * @param url 接口地址
         * @param params 请求参数
         * @param observer  接口回调
         */

        @SuppressLint("CheckResult")
        public fun post(lifecycleOwner: LifecycleOwner, url: String?, params: Map<String?, Any?>?, observer: MyObserver<BaseResponse<*>?>){

            RetrofitUtils.getApiUrl()
                ?.postUser(url, convertMapToBody(params),HashMap())!!.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<*>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<*>) {
                        observer.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        observer.onFailure(e, RxExceptionUtil.exceptionHandler(e!!))
                    }

                })

        }



        /**
         * post 请求
         * @param lifecycleOwner  生命周期对象
         * @param url 接口地址
         * @param params 请求参数
         * @param observer  接口回调
         */

        @SuppressLint("CheckResult")
        public fun postList(lifecycleOwner: LifecycleOwner, url: String?, list:List<Any?>, observer: MyObserver<BaseResponse<*>?>){

            RetrofitUtils.getApiUrl()
                ?.postUser(url, convertListToBody(list),HashMap())!!.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<*>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<*>) {
                        observer.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        observer.onFailure(e, RxExceptionUtil.exceptionHandler(e!!))
                    }

                })

        }




        /**
         * delete 请求
         */

        @SuppressLint("CheckResult")
        fun delete(lifecycleOwner: LifecycleOwner, url:String?, params: Map<String?, Any?>?, observer: MyObserver<BaseResponse<*>?>){

            RetrofitUtils.getApiUrl()?.delete(url, convertMapToBody(params),HashMap())?.subscribeOn(Schedulers.io())?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<*>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<*>) {
                        observer.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        observer.onFailure(e, RxExceptionUtil.exceptionHandler(e!!))
                    }

                })

        }


        /**
         * Put 请求 ,这个可能是属于表单类型的提交
         *
         * @param context
         * @param
         */
        @SuppressLint("CheckResult")
        fun put(
            lifecycleOwner: LifecycleOwner,
            url: String?,
            params: Map<String?, Any?>?,
            headsMap: Map<String?, String?>?,
            observer: MyObserver<BaseResponse<*>?>
        ) {

            RetrofitUtils.getApiUrl()?.put(url, convertMapToBody(params),HashMap())?.subscribeOn(Schedulers.io())?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<*>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<*>) {
                        observer.onSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        observer.onFailure(e, RxExceptionUtil.exceptionHandler(e!!))
                    }

                })


        }


        /**
         * 上传多张图片，多份文件
         *
         * @param files
         */
        fun upLoadImgs(
            lifecycleOwner: LifecycleOwner,
            url: String?,
            Filekey: String?,
            files: List<File>,
            observer: MyObserver<BaseResponse<*>?>
        ) {
            val header: Map<String, String> =
                HashMap()
            val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM) //表单类型
            for (i in files.indices) {
                val file: File = files[i]
                val photoRequestBody: RequestBody =
                   RequestBody. create("multipart/form-data".toMediaTypeOrNull(), file)
                builder.addFormDataPart(Filekey!!, file.getName(), photoRequestBody)
            }
            val parts: List<MultipartBody.Part?> = builder.build().parts
            RetrofitUtils.getApiUrl()!!.uploadImages(url, parts)
                ?.subscribeOn(Schedulers.io())?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<*>?> {
                    override fun onSubscribe(d: Disposable) {

                        canCenRequest(lifecycleOwner,d)

                    }


                    override fun onError(e: Throwable) {
                        observer.onFailure(e, e.toString())
                    }

                    override fun onComplete() {

                    }
                    override fun onNext(t: BaseResponse<*>) {
                        observer.onSuccess(t)
                    }
                })
        }


        /**
         * 将map数据转换为 普通的 json RequestBody上传参数给服务器
         *
         * @param map 以前的请求参数
         * @return
         */
        fun convertMapToBody(map: Map<*, *>?): RequestBody? {
            return RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), Gson().toJson(map))
        }

        fun convertListToBody(list: List<Any?>?): RequestBody? {
            return RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), Gson().toJson(list))
        }


        /**
         * 将map数据转换为图片，文件类型的  RequestBody上传参数给服务器
         *
         * @param map 以前的请求参数
         * @return 待测试
         */
        fun convertMapToMediaBody(map: Map<*, *>?): RequestBody? {
            return RequestBody.create(
                "multipart/form-data; charset=utf-8".toMediaTypeOrNull(),
                JSONObject(map).toString()
            )
        }



        //监控生命周期取消订阅
        public fun canCenRequest(
            lifecycleOwner: LifecycleOwner,
            disposable: Disposable?
        ){
            var lifeEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
            //监控页面的生命周期
            lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (lifeEvent == event) {
                        if (disposable!=null&&disposable!!.isDisposed){  //取消订阅
                            disposable!!.dispose();
                        }
                        lifecycleOwner.lifecycle.removeObserver(this)
                    }
                }
            })
        }




    }
}




