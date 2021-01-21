package com.example.commonnetwork

annotation class Url {
    //设置默认超时时间
    companion object {
        val DEFAULT_TIME:Long = 10
        var BaseUrl:String="https:/www.baidu.com/"
    }
}