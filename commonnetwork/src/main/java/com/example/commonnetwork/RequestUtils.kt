package com.example.commonnetwork

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.commonnetwork.bean.BaseResponse
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.*
import java.util.logging.Logger


//网络请求公共类
class RequestUtils {
    companion object {

        /**
         * Get 请求
         * @param lifecycleOwner  生命周期对象
         * @param url 接口地址
         * @param params 请求参数
         * @param observer  接口回调
         */

        @SuppressLint("CheckResult")
        fun get(
            lifecycleOwner: LifecycleOwner,
            url: String?,
            params: HashMap<String, Any>,
            observer: BaseObserver<Any>

        ) {
            com.orhanobut.logger.Logger.v("request---body:",Gson().toJson(params))
            RetrofitUtils.getApiUrl()
                ?.getUser(url, params)!!.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<Any>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                          canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<Any>) {
                        if (t.code === "200") {
                            observer.onSuccess(Gson().toJson(t.data))
                        } else {
                            observer.onFailure(null, t.msg)
                        }


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
        public fun post(
            lifecycleOwner: LifecycleOwner,
            url: String?,
            params: Map<String?, Any?>?,
            observer: BaseObserver<Any>
        ) {

            RetrofitUtils.getApiUrl()
                ?.postUser(url, convertMapToBody(params), HashMap())!!.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<Any>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<Any>) {
                        if (t.code === "200") {
                            observer.onSuccess(Gson().toJson(t.data))
                        } else {
                            observer.onFailure(null, t.msg)
                        }
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
        public fun postList(
            lifecycleOwner: LifecycleOwner,
            url: String?,
            list: List<Any?>,
            observer: BaseObserver<Any>
        ) {

            RetrofitUtils.getApiUrl()
                ?.postUser(url, convertListToBody(list), HashMap())!!.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<Any>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<Any>) {
                        if (t.code === "200") {
                            observer.onSuccess(Gson().toJson(t.data))
                        } else {
                            observer.onFailure(null, t.msg)
                        }
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
        fun delete(
            lifecycleOwner: LifecycleOwner,
            url: String?,
            params: Map<String?, Any?>?,
            observer: BaseObserver<Any>
        ) {

            RetrofitUtils.getApiUrl()?.delete(url, convertMapToBody(params), HashMap())
                ?.subscribeOn(Schedulers.io())?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<Any>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<Any>) {
                        if (t.code === "200") {
                            observer.onSuccess(Gson().toJson(t.data))
                        } else {
                            observer.onFailure(null, t.msg)
                        }
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
            observer: BaseObserver<Any>
        ) {

            RetrofitUtils.getApiUrl()?.put(url, convertMapToBody(params), HashMap())
                ?.subscribeOn(Schedulers.io())?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<Any>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        canCenRequest(lifecycleOwner, d)
                    }

                    override fun onNext(t: BaseResponse<Any>) {
                        if (t.code === "200") {
                            observer.onSuccess(Gson().toJson(t.data))
                        } else {
                            observer.onFailure(null, t.msg)
                        }
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
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
                builder.addFormDataPart(Filekey!!, file.getName(), photoRequestBody)
            }
            val parts: List<MultipartBody.Part?> = builder.build().parts
            RetrofitUtils.getApiUrl()!!.uploadImages(url, parts)
                ?.subscribeOn(Schedulers.io())?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : Observer<BaseResponse<*>?> {
                    override fun onSubscribe(d: Disposable) {

                        canCenRequest(lifecycleOwner, d)

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
         * 下载文件
         *
         * @param files
         */
        //下载文件,url  接口地址   filename:文件名称   observer：回调
        open fun DownLoad(
            url: String?,
            filename: String?,
            observer: MyObserver<File?>
        ): Unit {
            RetrofitUtils.getApiUrl()!!.download(url)!!.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(object : Observer<ResponseBody> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(baseResponse: ResponseBody) {
                        observer.onSuccess(saveFile(filename, baseResponse))
                    }

                    override fun onError(e: Throwable) {
                        observer.onFailure(e, e.toString())
                    }

                    override fun onComplete() {}
                })
        }


        //保存文件
        fun saveFile(fileName: String?, body: ResponseBody): File? {
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            var file: File? = null
            try {
                if (fileName == null) {
                    return null
                }
                file = File(Environment.getExternalStorageDirectory().getPath(), fileName)
                if (file == null || !file.exists()) {
                    file!!.createNewFile()
                }
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                val fileReader = ByteArray(4096)
                inputStream = body.byteStream()
                outputStream = FileOutputStream(file)
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                }
                outputStream.flush()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return file
        }


        /**
         * 将map数据转换为 普通的 json RequestBody上传参数给服务器
         *
         * @param map 以前的请求参数
         * @return
         */
        fun convertMapToBody(map: Map<*, *>?): RequestBody? {
            com.orhanobut.logger.Logger.v(Gson().toJson(map))
            return RequestBody.create(
                "application/json; charset=utf-8".toMediaTypeOrNull(),
                Gson().toJson(map)
            )
        }

        fun convertListToBody(list: List<Any?>?): RequestBody? {
            com.orhanobut.logger.Logger.v(Gson().toJson(list))
            return RequestBody.create(
                "application/json; charset=utf-8".toMediaTypeOrNull(),
                Gson().toJson(list)
            )
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
        ) {
            var lifeEvent: Lifecycle.Event = Lifecycle.Event.ON_DESTROY
            //监控页面的生命周期
            lifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (lifeEvent == event) {
                        if (disposable != null && disposable!!.isDisposed) {  //取消订阅
                            disposable!!.dispose();
                        }
                        lifecycleOwner.lifecycle.removeObserver(this)
                    }
                }
            })
        }


    }
}




