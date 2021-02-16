package com.student.drop.ui.vm

import com.develop.wallet.eth.WalletManager
import com.student.drop.base.base_vm.BViewModel
import com.student.drop.bean.MyPageState

open class MainVM(val mRepo: MainRepo) : BViewModel(mRepo) {
    /**
     * 检查当前地址中对应的代币余额等信息
     * myAddress 自己的地址
     * tokenAddres 代币的合约地址
     */
    fun checkAddressInfo(myAddress: String, mTokenAddres: String) {
        mRepo.mPageStateMLD.postValue(MyPageState(isDialogLoding = true))
        launchOnIO {
            // 设置代币合约地址
            WalletManager.tokenAddres = mTokenAddres
            // 获取版本信息
            WalletManager.getClientVersion()
            // ethBalance 以太坊余额
            WalletManager.getEthBalance(myAddress)
            // noce
            WalletManager.getNonce(myAddress)
            // name 报错该方法
            WalletManager.getTokenName(myAddress)
            // symbol
            WalletManager.getTokenSymbol(myAddress)
            // decimals
            WalletManager.getTokenDecimals(myAddress)
            // totalsupply
            WalletManager.getTokenTotalSupply(myAddress)
            // token balance 代币余额
            WalletManager.getTokenBalance(myAddress)
            mRepo.mPageStateMLD.postValue(MyPageState(isDialogLoding = false))
        }
    }

    /**
     * 根据助记词转账
     *
     * @param fromAddress
     * @param mnemonic
     * @param toAddress
     * @param amount
     * @return
     */
    fun sendTransactionByMnemonic(
        fromAddress: String,
        mnemonic: String,
        toAddress: String,
        amount: String
    ) {
        mRepo.mPageStateMLD.postValue(MyPageState(isDialogLoding = true))
        launchOnIO {
            WalletManager.sendTransactionByMnemonic(
                fromAddress,
                mnemonic,
                toAddress,
                amount
            )
            mRepo.mPageStateMLD.postValue(MyPageState(isDialogLoding = false))
        }
    }


}

