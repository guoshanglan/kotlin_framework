package com.example.homemodule.fragement

import com.example.basemodule.BaseFragment
import com.example.commonnetwork.Test
import com.example.homemodule.BR
import com.example.homemodule.R
import com.example.homemodule.databinding.HomemoduleFragment1Binding
import com.example.homemodule.viewmodel.TestViewModel

class Fragment1 : BaseFragment<HomemoduleFragment1Binding, TestViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.homemodule_fragment1
    }

    override fun initView() {
        mViewModel.getData()
        Test().getData()


    }

    override fun getViewModel(): TestViewModel {

        return TestViewModel();
    }

    override fun getBindingVariable(): Int {

        return BR.fragment1
    }
}