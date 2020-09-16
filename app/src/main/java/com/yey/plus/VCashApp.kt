package com.yey.plus

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.tencent.mmkv.MMKV
import com.yey.plus.di.mVCashAM
import com.yey.plus.widget.LoadSirPageDateErr
import com.yey.plus.widget.LoadSirPageLoading
import com.yey.plus.widget.LoadSirPageNetErr
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates


class VCashApp : MultiDexApplication() {

    companion object {
        var CONTEXT: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = applicationContext
        initLoadSir()
        MMKV.initialize(this)
        initKoin()
    }

    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(LoadSirPageLoading())
            .addCallback(LoadSirPageNetErr())
            .addCallback(LoadSirPageDateErr())
            .setDefaultCallback(SuccessCallback::class.java)
            .commit()
    }
    private fun initKoin() {
        startKoin {
            androidContext(this@VCashApp)
            modules(mVCashAM)
        }
    }
}