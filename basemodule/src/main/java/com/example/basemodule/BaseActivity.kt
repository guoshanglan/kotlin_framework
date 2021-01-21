package com.example.basemodule

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.basemodule.callback.IBaseView
import com.example.basemodule.viewmodel.SuperViewModel
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity<B : ViewDataBinding, VM : SuperViewModel<IBaseView>>: AppCompatActivity(),
    IBaseView {

    /**获取布局*/
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**初始布局*/
    abstract fun initView()

    /**获取viewModel*/
    abstract fun getViewModel(): VM

    /**获取variable*/
    abstract fun getBindingVariable(): Int

    lateinit var mViewModel: VM
    lateinit var mViewDataBinding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        performDataBinding()
        if (setStatusBar()){
            initStatusBar()
        }
        initView()

    }

    protected open fun setStatusBar(): Boolean = true

    protected open fun initStatusBar() {
        //初始化状态栏
        ImmersionBar.with(this)
            .statusBarColor(R.color.white)
            .navigationBarColor(R.color.white)
            .autoStatusBarDarkModeEnable(true, 0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
            .fitsSystemWindows(true)
            .autoNavigationBarDarkModeEnable(true, 0.2f).keyboardEnable(true).init()
    }


    private fun performDataBinding() {
        //viewDataBinding关联生命周期

        mViewDataBinding.lifecycleOwner = this

        //获取ViewModel
        mViewModel = getViewModel()
        //viewModel关联Activity的生命周期
        mViewModel.attachLifecycle(lifecycle)
        mViewModel.let { lifecycle.addObserver(it) }
        //添加视图操作
        mViewModel.attachUI(this)
        //绑定数据
        if (getBindingVariable() > 0) {
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        }
    }


}