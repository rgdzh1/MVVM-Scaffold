package com.student.drop.ui

import com.blankj.utilcode.util.ToastUtils
import com.student.drop.R
import com.student.drop.base.base_view.BVMActivity
import com.student.drop.bean.MyPageState
import com.student.drop.databinding.ActivityMainBinding
import com.student.drop.ui.vm.MainVM
import com.student.drop.util.MySPUtils
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BVMActivity<ActivityMainBinding, MainVM>() {
    override fun getVM(): Lazy<MainVM> = viewModel()
    override fun layoutId(): Int = R.layout.activity_main
    override fun initListener() {
        super.initListener()
        mBinding.btnStart.setOnClickListener {
            mVM.value.mRepo.mPageStateMLD.postValue(MyPageState(isDialogLoding = true))
            mVM.value.processWork()
        }
    }


    //两秒内连续按2次退出
    var mExitTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtils.showLong("再按一次回退键关闭应用!")
            mExitTime = System.currentTimeMillis()
        } else {
            finish()
        }
    }
}