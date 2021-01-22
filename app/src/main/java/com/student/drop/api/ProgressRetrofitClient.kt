package com.student.drop.api

import com.blankj.utilcode.util.LogUtils
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jkt.tnetprogress.*
import com.student.drop.VCashApp
import com.student.drop.bean.MyProgressInfo
import okhttp3.OkHttpClient

/**
 * 创建一个可以监听上传和下载进度的OkHttpClient
 */
object ProgressRetrofitClient : BRetrofitClient() {
    val mBApi by lazy {
        getApi(ApiService::class.java, ApiService.B_URL)
    }

    /**
     *  重新配置
     */
    override fun myConfig(mBuilder: OkHttpClient.Builder) {
        // 使用cookie
        mBuilder.cookieJar(
            PersistentCookieJar(
                SetCookieCache(),
                SharedPrefsCookiePersistor(VCashApp.CONTEXT)
            )
        )

        mBuilder.addNetworkInterceptor(UploadInterceptor(object : OnUploadListener {
            override fun onUpLoadProgress(info: ProgressInfo?) {
                // 上传进度监听
                val progress = (100.0 * info!!.currentLength / info.contentLength).toInt()
                LiveEventBus.get("VCashApp-CircleProgressBar")
                    .post(MyProgressInfo(progress, 1))
                if (BuildConfig.DEBUG) {
                    LogUtils.e("上传进度", progress)
                }
            }

            override fun onUploadGetContentLengthFail(info: ProgressInfo?) {
            }
        }))
        mBuilder.addNetworkInterceptor(DownloadInterceptor(object : OnDownloadListener {
            override fun onDownLoadGetContentLengthFail(info: ProgressInfo?) {
                // 下载进度监听
                val progress = (100.0 * info!!.currentLength / info.contentLength).toInt()
                LiveEventBus.get("VCashApp-CircleProgressBar")
                    .post(MyProgressInfo(progress, 2))
                if (BuildConfig.DEBUG) {
                    LogUtils.e("下载进度", progress)
                }
            }

            override fun onDownLoadProgress(info: ProgressInfo?) {
            }
        }))
    }

}