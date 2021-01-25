package com.example.homemodule

class AdvertisementBean(
    var advertisingId:String?,  //广告Id
    var  adName:String?,   //广告名称
    var imageUrl:String?,  //图片路径
    var sourceUrl:String?,  //跳转链接
    var courseId:String?,  //关联课程
    var showTime:String?,   //展示时长
     var courseType:Int   //课程类型
) {
    override fun toString(): String {
        return "AdvertisementBean(advertisingId=$advertisingId, adName=$adName, imageUrl=$imageUrl, sourceUrl=$sourceUrl, courseId=$courseId, showTime=$showTime, courseType=$courseType)"
    }
}