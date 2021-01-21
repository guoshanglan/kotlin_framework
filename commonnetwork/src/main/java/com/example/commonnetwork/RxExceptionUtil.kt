package com.example.commonnetwork

import org.json.JSONException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException


class RxExceptionUtil {

    companion object {
        fun exceptionHandler(e: Throwable): String? {
            var errorMsg: String? = "未知错误"
            if (e is UnknownHostException) {
                errorMsg = "网络不可用"
            } else if (e is SocketTimeoutException) {
                errorMsg = "请求网络超时"
            } else if (e is HttpException) {
                val httpException: HttpException = e as HttpException
                errorMsg = convertStatusCode(httpException)
            } else if (e is ParseException || e is JSONException
                || e is JSONException
            ) {
                errorMsg = "数据解析错误"
            }
            return errorMsg
        }

        private fun convertStatusCode(httpException: HttpException): String? {
            val msg: String
            msg = httpException.message()
            return msg
        }
    }
}