package com.example.commonnetwork

import android.util.Log.INFO
import androidx.annotation.NonNull
import androidx.multidex.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitUtils {
    companion object {
        private val TAG = "RetrofitUtils"
        private var mApiSerivce: ApiService? = null

        /**
         * 单例模式
         */

        fun getApiUrl(): ApiService? {
            if (mApiSerivce == null) {
                synchronized(RetrofitUtils::class.java) {
                    if (mApiSerivce == null) {
                        mApiSerivce = initRetrofit(initOkHttp()).create(ApiService::class.java)
                    }
                }
            }
            return mApiSerivce
        }


        private fun RetrofitUtils() {}


        /**
         * 初始化Retrofit
         */
        @NonNull
        private fun initRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(Url.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        /**
         * 初始化okhttp
         */
        @NonNull
        private fun initOkHttp(): OkHttpClient {
            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.readTimeout(Url.DEFAULT_TIME, TimeUnit.SECONDS) //设置读取超时时间
                .connectTimeout(Url.DEFAULT_TIME, TimeUnit.SECONDS) //设置请求超时时间
                .writeTimeout(Url.DEFAULT_TIME, TimeUnit.SECONDS) //设置写入超时时间
                .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
                httpClientBuilder.addInterceptor(LogInterceptor())


            return httpClientBuilder.build()

        }

    }
}