package com.student.drop.ui

import com.student.drop.R
import com.student.drop.base.base_view.BVMActivity
import com.student.drop.bean.VCashPageState
import com.student.drop.databinding.ActivityMainBinding
import com.student.drop.ui.vm.MainVM
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BVMActivity<ActivityMainBinding, MainVM>() {
    override fun getVM(): Lazy<MainVM> = viewModel()
    override fun layoutId(): Int = R.layout.activity_main
    override fun initListener() {
        super.initListener()
        mBinding.btn.setOnClickListener {
            mVM.value.mRepo.mPageStateMLD.postValue(VCashPageState(isDialogLoding = true))
            mVM.value.requestWeb()
            mVM.value.mRepo.mPageStateMLD.postValue(VCashPageState(isDialogLoding = false))
        }
    }
}