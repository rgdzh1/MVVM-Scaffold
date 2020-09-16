package com.yey.plus.di
import org.koin.dsl.module

val viewModelModule = module {
//    viewModel { LoginVM(get()) }
}

val repositoryModule = module {
//    single { DefauleRetrofitClient.mBApi } // 常驻内存
//    single { ProgressRetrofitClient.mBApi }
//    factory { LoginRepo(get()) }
}

val mVCashAM = listOf(viewModelModule, repositoryModule)