package com.student.drop.api

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.student.drop.VCashApp
import okhttp3.OkHttpClient

object DefauleRetrofitClient : BRetrofitClient() {
    val mBApi by lazy {
        getApi(ApiService::class.java, ApiService.B_URL)
    }

    override fun myConfig(mBuilder: OkHttpClient.Builder) {
        // 使用cookie
        mBuilder.cookieJar(
            PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(VCashApp.CONTEXT)
            )
        )
    }
}