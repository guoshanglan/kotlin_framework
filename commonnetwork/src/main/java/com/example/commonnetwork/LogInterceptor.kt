package com.example.commonnetwork

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import java.lang.String
import java.util.*

class LogInterceptor:Interceptor {
    private val TAG = "okhttp"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        Log.e(TAG, "request:" + request.toString())
        val t1 = System.nanoTime()
        val response = chain.proceed(chain.request())
        val t2 = System.nanoTime()
        Log.e(
            TAG, String.format(
                Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request.url, (t2 - t1) / 1e6, response.headers
            )
        )
        val mediaType = response.body!!.contentType()
        val content = response.body!!.string()
        Log.e("response","----------------------------------------------------------")
        Log.e(TAG, "response body:$content")
        Log.e("response","----------------------------------------------------------")
        return response.newBuilder()
            .body(ResponseBody.create(mediaType, content))
            .build()
    }



}