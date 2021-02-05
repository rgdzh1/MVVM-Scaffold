package com.student.drop.di

import com.student.drop.api.DefauleRetrofitClient
import com.student.drop.ui.vm.MainRepo
import com.student.drop.ui.vm.MainVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainVM(get()) }
}

val repositoryModule = module {
    single { DefauleRetrofitClient.mBApi } // 常驻内存
//    single { ProgressRetrofitClient.mBApi }
    factory { MainRepo(get()) }
}

val mVCashAM = listOf(viewModelModule, repositoryModule)