package com.example.homemodule.viewmodel

import androidx.databinding.ObservableField
import com.example.basemodule.viewmodel.BaseViewModel
import com.example.commonnetwork.BaseObserver
import com.example.commonnetwork.RequestUtils
import com.example.commonnetwork.Url
import com.example.homemodule.AdvertisementBean

class TestViewModel:BaseViewModel() {
    var fragmentName: ObservableField<String> =ObservableField<String>("fragment1")

    fun getData(){
        var params =HashMap<String, Any>();

        RequestUtils.get(this,Url.testurl,params,object: BaseObserver<AdvertisementBean>() {

            override fun onSuccess(result: AdvertisementBean) {

            }

            override fun onFailure(e: Throwable?, errorMsg: String?) {

            }


        })
    }

}