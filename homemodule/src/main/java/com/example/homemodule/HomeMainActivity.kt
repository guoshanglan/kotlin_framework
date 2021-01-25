package com.example.homemodule

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.basemodule.BaseActivity
import com.example.basemodule.adapter.ListFragmentAdapter
import com.example.basemodule.viewmodel.NoViewModel
import com.example.commonmodule.utils.JumpUtils
import com.example.homemodule.databinding.HomemoduleHomemainActivityBinding
import com.example.homemodule.fragement.Fragment1

@Route(path = JumpUtils.ACTIVITY_HOME)
class HomeMainActivity : BaseActivity<HomemoduleHomemainActivityBinding, NoViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.homemodule_homemain_activity
    }

    override fun initView() {
        val fragmentsInCache = supportFragmentManager.fragments
        val listFragment = ArrayList<Fragment>()
        listFragment.add(Fragment1())
        listFragment.add(Fragment1())
        listFragment.add(Fragment1())
        listFragment.add(Fragment1())

        mViewDataBinding.viewPager.offscreenPageLimit = 1

        mViewDataBinding.viewPager.isUserInputEnabled = false
        mViewDataBinding.viewPager.adapter =
            ListFragmentAdapter(supportFragmentManager, lifecycle, listFragment)
        mViewDataBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> mViewDataBinding.rbTable1.toggle()
                    1 -> mViewDataBinding.rbTable2.toggle()
                    2 -> mViewDataBinding.rbTable3.toggle()
                    3 -> mViewDataBinding.rbTable4.toggle()
                }
            }
        })



        mViewDataBinding.rgTable.setOnCheckedChangeListener { group, checkedId ->
            run {
                when (checkedId) {
                    mViewDataBinding.rbTable1.id -> {

                        initStatusBar(false)


                    }
                    mViewDataBinding.rbTable2.id -> {

                        initStatusBar(true)

                    }
                    mViewDataBinding.rbTable3.id -> {

                        initStatusBar(true)


                    }
                    mViewDataBinding.rbTable4.id -> {
                        initStatusBar(false)

                    }
                }
            }
        }


        mViewDataBinding.rbTable1.toggle()


    }

    override fun getViewModel(): NoViewModel {

        return NoViewModel()
    }

    override fun getBindingVariable(): Int {

        return 0;
    }


}