package com.student.drop.ui.vm

import com.blankj.utilcode.util.*
import com.student.drop.BuildConfig
import com.student.drop.base.base_vm.BViewModel
import com.student.drop.bean.MyPageState
import com.student.drop.util.MySPUtils
import kotlinx.coroutines.delay
import org.apache.commons.lang3.RandomStringUtils
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
            cookiesMap["Cookie"] ="rid=eyJpdiI6ImRwZW9RcG9XYitaQ0NuK0xvRURTSVE9PSIsInZhbHVlIjoiblVEcERNd0FQbzJGVnR6eWVNWGdQZz09IiwibWFjIjoiZDVmNTgyYmM1NDNiNzBlNWU0NTlkMjA1N2UwNWYwZTdhMGZlOWUwMThjNTNhZTljY2JkY2IwYmRiNjNiMzMwNSJ9; __zlcmid=12QjmBN21N7pB8L; dismissed-search-ext=true; remember_web_59ba36addc2b2f9401580f014c7f58ea4e30989d=eyJpdiI6IlFQcDJWT05XbHRGQ09ZV2MwT1hBOXc9PSIsInZhbHVlIjoiSFM4OTR3OEZpREM3WFwvOVE3NndrSTVJenc4NFwvT3dOZGtlVWF2Wlc5QjBHVGRucTkzT2Z6OFlHYjFTXC9id1lnU0Zqb3RkRnh0NXdFdXYwRzlhQTRicFVDZlh5ZStDTG5pbFwvem5SR2dwMkZIZTlGR1wvbTEycnpuU3R1bVVrZWFQaTV3aTQ3Tnp2UisxNkl0ckRlMEtnQnprQWV3aTZ6QnUxT3R1ekNCQWZtd1c3bStCOVVoSXhuVFBuNlpYRldseHoiLCJtYWMiOiI2MzAzYTYyY2Y0OTA5OTE2OTlhYTZhMTljMGE5M2JkOTYwZTFkNmEwODUyZmYxM2FhMjJkNTQ4NGM4MzBmNTU2In0%3D; XSRF-TOKEN=eyJpdiI6Im5uMkY4QUJBN2lOcW1KWlpoaUJmWnc9PSIsInZhbHVlIjoiSndsdldwejVaUk1IbVUrdlRlS0d3amxtTXVWazlpTGVqaU9iVzhGT2VtdG53R3kxcUlcL2gxa0hWbDJMTCtJQ1YiLCJtYWMiOiJmNjRiOWVlNmE4YWI3NzllZmZlZTNkMTJjMjg2MTU0NmJmMWZmNTRhZDM1NDUwYmVkMjA2MzBlNjVjZjM3MmI3In0%3D; presearch_session=eyJpdiI6Ino2cVMxWnJwTENXempWU3lWakF5Q2c9PSIsInZhbHVlIjoibks0d1I0U0RnTW4rcFwvcU82dFRkTnVrMUpkcTRmaUxtWkcydW5pOWUwRCtWZk9kTnZ6WmZnZVRiZGRDNWhvTjQiLCJtYWMiOiIwMjg3MGU0OTA5ZDVjMDczODc0NTVjYWM2M2IyNDA5ZDg4ODNlMWViYjA5OGE2NmI1NzE1MThiNWYzMTA3M2NjIn0%3D"
            var document = Jsoup.connect(mUrl).cookies(cookiesMap).get()
            // LogUtils.e("第 $count 次请求结果", document)
            delay(3000)
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

