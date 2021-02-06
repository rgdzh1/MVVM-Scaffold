package com.student.drop.ui.vm

import com.blankj.utilcode.util.*
import com.student.drop.BuildConfig
import com.student.drop.base.base_vm.BViewModel
import com.student.drop.bean.MyPageState
import com.student.drop.util.MySPUtils
import kotlinx.coroutines.delay
import org.jsoup.Jsoup
import java.net.CookieHandler
import java.util.*
import kotlin.math.roundToInt

open class MainVM(val mRepo: MainRepo) : BViewModel(mRepo) {
    private var mHandlerInfo = mutableListOf<String>()
    var mCount = 100
    /**
     * 执行
     */
    fun processWork() {
        launchOnIO {
            val mInfoMap = getSearchInfo()
            val mRandomNumber = Math.random() * (mInfoMap.size - 2)
            val mIndex = mRandomNumber.roundToInt()
            val mUrl = "https://engine.presearch.org/search?q=${mInfoMap[mIndex]}"
             LogUtils.e("Presearch 第 $mCount 次请求链接 $mUrl")
            val cookiesMap = HashMap<String, String>()
            cookiesMap["Cookie"] ="""rid=eyJpdiI6ImRwZW9RcG9XYitaQ0NuK0xvRURTSVE9PSIsInZhbHVlIjoiblVEcERNd0FQbzJGVnR6eWVNWGdQZz09IiwibWFjIjoiZDVmNTgyYmM1NDNiNzBlNWU0NTlkMjA1N2UwNWYwZTdhMGZlOWUwMThjNTNhZTljY2JkY2IwYmRiNjNiMzMwNSJ9; __zlcmid=12QjmBN21N7pB8L; remember_web_59ba36addc2b2f9401580f014c7f58ea4e30989d=eyJpdiI6IlFQcDJWT05XbHRGQ09ZV2MwT1hBOXc9PSIsInZhbHVlIjoiSFM4OTR3OEZpREM3WFwvOVE3NndrSTVJenc4NFwvT3dOZGtlVWF2Wlc5QjBHVGRucTkzT2Z6OFlHYjFTXC9id1lnU0Zqb3RkRnh0NXdFdXYwRzlhQTRicFVDZlh5ZStDTG5pbFwvem5SR2dwMkZIZTlGR1wvbTEycnpuU3R1bVVrZWFQaTV3aTQ3Tnp2UisxNkl0ckRlMEtnQnprQWV3aTZ6QnUxT3R1ekNCQWZtd1c3bStCOVVoSXhuVFBuNlpYRldseHoiLCJtYWMiOiI2MzAzYTYyY2Y0OTA5OTE2OTlhYTZhMTljMGE5M2JkOTYwZTFkNmEwODUyZmYxM2FhMjJkNTQ4NGM4MzBmNTU2In0%3D; XSRF-TOKEN=eyJpdiI6InpVQWl6YmlLRHN6UTBnOEl5c3YwRXc9PSIsInZhbHVlIjoicktkRVlhT1VaRkR4WmRnNm05dkhaN1FYMjNOQW5TYjhBc1lkdHFUczRKV3QyZEtMRUZ0cVhvYjRoMVJMczhlNiIsIm1hYyI6ImFlODc5ZmUwNjIyMzJjMWU0YTkzNzM0ZWFhMWIyYjViMTk4OGQ5ZGNkNDVhNzUwYTg3NGM1OTUzOTQzZmU2NTkifQ%3D%3D; presearch_session=eyJpdiI6IjdJOGcwM2VYS1VmK0FMQzhQUndnVXc9PSIsInZhbHVlIjoiXC9XNnBmb29cL3lQc1lidjVVS0g3YUFmYk5WVDlQaU16R1NMZ3FQKzMyUWRGQU9yM1VnYkRnUnhBcWJwQVc4a2pFIiwibWFjIjoiMGE0NDVlODk2MWIwYTAyZGRhYWVjMmFiYjhhODZiNWJmMzY4MzlkZmFmNjlkZjg1YmI3NDhhMzQxOWE2M2E5MCJ9; token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE2MTI2MDkwMTEsImV4cCI6MTYxMzIxMzgxMSwidXNlcl9pZCI6MjA3NTI1MSwidXNlcl9hcGlfa2V5IjoicDRqeUs4UXZYTlA0ZXFSQjRGanh3aFkxMWpqcWxnbWJRMkNUT1pINVFEMXNhWXFtQmZMZlExMjZBbmh6dDVZMHBZZGZLSDBvZVFtTjhtWm9XTUt4WEFRWVVBYXYyTksyQmFOOVg1cjBYU3dabE9ydjRxWmFxb2YxZjQzaGFsZWJZN3UyRjQ2QjIwT1AxaFBFRXAxSm5iIiwiYmV0YXMiOltdfQ.rJ6yA69eM36K25PPo8PzB02E3u4HUDimbAeUy2dq6uswL4ErDYvCFFP99zTa8E5rxUHh7jVh_Ey8oCmWgCy29TXv6XMUEUsuEJyJP-ronGNQ_PoyRO5otgXfGeTvwPcgctTZYNpN-uw6wwRclE8ye3lyrVpBAmXzIOYR-sKFWOERIpOiZal9_gCl7s-qP2pRFXrFaHG9eXLk6Rpm2CH9zim2a45rDGQY2G1HAZIC2mHxSKKuVjVH1MosyKH2ARsPn54Bp48jYIE8C29lEiIPruVqmVLK8sV_o7K8HJzALOauOkbz40eY-CuJopHoD01sldVYCPF6p1xfa4kcNtzeiQ"""
            var document = Jsoup.connect(mUrl).cookies(cookiesMap).get()
            // LogUtils.e("第 $count 次请求结果", document)
            delay(20000)
            mCount--
            if (mCount < 0) {
                // LogUtils.e("Presearch 退出")
                mRepo.mPageStateMLD.postValue(MyPageState(isDialogLoding = false))
                return@launchOnIO
            }
            processWork()
        }
    }

    /**
     * 获取处待处理的搜索数据
     */
    private fun getSearchInfo(): MutableList<String> {
        if (mHandlerInfo.size == 0) {
            val mStrInfo = ResourceUtils.readAssets2String("info")
            val mInfoArr = mStrInfo.split(System.lineSeparator())
            mInfoArr.forEach {
                if (!it.isNullOrBlank()) {
                    mHandlerInfo.add(it)
                }
            }
        }
        return mHandlerInfo
    }
}

