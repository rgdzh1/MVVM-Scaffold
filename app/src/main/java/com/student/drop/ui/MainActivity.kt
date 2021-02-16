package com.student.drop.ui

import com.blankj.utilcode.util.ToastUtils
import com.develop.wallet.eth.WalletManager
import com.student.drop.R
import com.student.drop.base.base_view.BVMActivity
import com.student.drop.databinding.ActivityMainBinding
import com.student.drop.ui.vm.MainVM
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BVMActivity<ActivityMainBinding, MainVM>() {
    override fun getVM(): Lazy<MainVM> = viewModel()
    override fun layoutId(): Int = R.layout.activity_main
    override fun initListener() {
        super.initListener()
        mBinding.btnCheck.setOnClickListener {
            mVM.value.checkAddressInfo(
                "0xb47998cea1cbe1a17b105950ba6932794ffc9ba7",
                "0xb47998cea1cbe1a17b105950ba6932794ffc9ba7"
            )
        }
        mBinding.btnTranMnem.setOnClickListener {
            mVM.value.sendTransactionByMnemonic(
                "0x6b2bd478dfce5fa35f7ebb7b0152d295d75627f9",
                "apology kangaroo acid primary country rival ankle plastic bag agree section only pumpkin fly popular session shift silly toward desk cactus rug grass figure",
                "0x3f8a2946ed7E7d911ceb0bF668060aaC2F9c6168",
                "1"
            )
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