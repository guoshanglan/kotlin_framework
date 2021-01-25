package com.example.commonnetwork.bean


/**
 * 接口数据统一返回类
 */
class BaseResponse<T>(
    val code: String,
    val msg: String,
    val success: Boolean,
    val data: T

) {

    override fun toString(): String {
        return "BaseResponse(code='$code', msg='$msg', success=$success, data=$data)"
    }
}