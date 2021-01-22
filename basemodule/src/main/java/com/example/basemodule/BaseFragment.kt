package com.example.basemodule

import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.basemodule.callback.IBaseView
import com.example.basemodule.callback.ICreateFragment
import com.example.basemodule.viewmodel.SuperViewModel

abstract class BaseFragment<B : ViewDataBinding, VM : SuperViewModel<IBaseView>> : Fragment(),
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
    private var mICreateFragment: ICreateFragment? = null   //fragment创建回调


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        return  mViewDataBinding.root
    }


    /**
     * 解决软键盘调起导航栏的bug
     */
    protected fun hideNavigationBar() {
        val window: Window = requireActivity().window
        val params: WindowManager.LayoutParams = window.getAttributes()
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_FULLSCREEN
        window.attributes = params
        var uiFlags: Int = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN) // hide status bar
        uiFlags = uiFlags or 0x00001000 //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
        window.decorView.systemUiVisibility = uiFlags
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mICreateFragment?.onCreateFragment(view)
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
        initView()
    }

    //用来进行回调的
    open fun setOnCreateFragment(mICreateFragment: ICreateFragment) {
        this.mICreateFragment = mICreateFragment
    }

    override fun showContent() {

    }

    override fun showLoading() {

    }

    override fun onRefreshEmpty() {

    }

    override fun onRefreshFailure(message: String?) {

    }

    override fun onRefreshNoNetwork() {

    }


}