package com.student.drop.api

import com.blankj.utilcode.util.LogUtils
import com.google.gson.GsonBuilder
import com.student.drop.BuildConfig
import com.student.drop.util.EZLoanSPUtils
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


abstract class BRetrofitClient {
    private var mLog = HttpLoggingInterceptor { message ->
        if (BuildConfig.DEBUG) {
            LogUtils.e("VCash 日志", message)
        }
    }
    private val mOkHttpClient: OkHttpClient
        get() {
            mLog.level = HttpLoggingInterceptor.Level.BODY
            mLog.redactHeader("Authorization");
            mLog.redactHeader("Cookie");
            val mBuilder = OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.MINUTES)
                .readTimeout(10L, TimeUnit.MINUTES)
                .writeTimeout(10L, TimeUnit.MINUTES)
                .addInterceptor(mLog)
                .addInterceptor {
                    // 添加公共参数
                    val original: Request = it.request()
                    val request = original.newBuilder()
                        .header("token", EZLoanSPUtils.decodeString(EZKEY.TOKEN))
                        .build()
                    it.proceed(request)
                }
                .retryOnConnectionFailure(true)
            myConfig(mBuilder)
            return mBuilder.build()
        }

    /**
     * 配置OkHttpClient.Builder对象
     */
    protected abstract fun myConfig(builder: OkHttpClient.Builder)

    /**
     * 创建不同API的代理对象
     */
    fun <T> getApi(apiClass: Class<T>, url: String): T {
        val mGson = GsonBuilder()
            .setDateFormat("MM-dd-yyyy HH:mm:ss")
            .serializeNulls()
            .setLenient()
            .create();
        return Retrofit.Builder()
            .baseUrl(url)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .build()
            .create(apiClass)
    }
}
//            .registerTypeAdapter(IdentityType::class.java,GsonEnumTypeAdapter(IdentityType.DRIVER_LICENSE))
//            .registerTypeAdapter(MaritalStatus::class.java,GsonEnumTypeAdapter(MaritalStatus.MARRIED))
//            .registerTypeAdapter(ReferenceRelation::class.java,GsonEnumTypeAdapter(ReferenceRelation.FAMILY))
