package com.example.homemodule.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.example.basemodule.viewmodel.BaseViewModel
import com.example.commonnetwork.BaseObserver
import com.example.commonnetwork.RequestUtils
import com.example.commonnetwork.Url
import com.example.commonnetwork.bean.BaseResponse
import com.example.homemodule.AdvertisementBean
import com.google.gson.Gson

class TestViewModel:BaseViewModel() {
    var fragmentName: ObservableField<String> =ObservableField<String>("fragment1")

    fun getData(){


        var params =HashMap<String, Any>();

        RequestUtils.get(this,Url.testurl,params,object: BaseObserver<Any>() {

            override fun onSuccess(result: Any) {
                val advertisementBean = Gson().fromJson(result.toString(),AdvertisementBean::class.java)


            }

            override fun onFailure(e: Throwable?, errorMsg: String?) {

            }


        })
    }

}


