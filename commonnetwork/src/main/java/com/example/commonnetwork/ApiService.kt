package com.example.commonnetwork

import com.example.commonnetwork.bean.BaseResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import retrofit2.http.Url


/**
 * retrofit 接口服务类
 */

interface ApiService {

    /**
     * get 请求
     */
     @GET
     @JvmSuppressWildcards
     fun getUser(@Url url: String?, @QueryMap info: Map<String, Any> ): Observable<BaseResponse<Any>>? //简洁方式   直接获取所需数据

    /**
     * TODO POST请求
     * @parmas url  请求地址
     * @params Body  利用body进行参数封装
     * @params headsMap  请求所需要的特殊请求头map<key></key>,value>集合，如果有特殊的header，没有的话请传控map就可以
     */
    @POST
    @JvmSuppressWildcards
    fun postUser(@Url url: String?, @Body body: RequestBody?, @HeaderMap headsMap: Map<String?, String?>?): Observable<BaseResponse<Any>>?

    /**
     * TODO DELETE
     */
    @DELETE
    @JvmSuppressWildcards
    fun delete(@Url url: String?, @Body body: RequestBody?, @HeaderMap headsMap: Map<String?, String?>?): Observable<BaseResponse<Any>>?

    /**
     * TODO PUT
     */
    @PUT
    @JvmSuppressWildcards
    fun put(@Url url: String?, @Body body: RequestBody?, @HeaderMap headsMap: Map<String?, String?>?): Observable<BaseResponse<Any>>?


    /**
     * 多文件上传
     */
    @Multipart
    @POST
    @JvmSuppressWildcards
    fun uploadImages(
        @Url url: String?,
        @Part files: List<MultipartBody.Part?>?
    ): Observable<BaseResponse<Any>>?

    /**
     *
     * 文件下载
     * @Streaming 这个注解必须添加，否则文件全部写入内存，文件过大会造成内存溢出
     */
    @Streaming
    @GET
    @JvmSuppressWildcards
    fun download(@Url url: String?): Observable<ResponseBody>?



}