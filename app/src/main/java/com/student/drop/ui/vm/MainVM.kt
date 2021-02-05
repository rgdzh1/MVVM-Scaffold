package com.student.drop.ui.vm

import com.blankj.utilcode.util.*
import com.student.drop.base.base_vm.BViewModel
import kotlinx.coroutines.delay
import org.jsoup.Jsoup
import java.util.*
import kotlin.math.floor
import kotlin.math.roundToInt

open class MainVM(val mRepo: MainRepo) : BViewModel(mRepo) {
    private var mHandlerInfo = mutableListOf<String>()
    var count = 100

    /**
     * 请求网页
     */
    fun requestWeb() {
        launchOnIO {
            val mInfoMap = getSearchInfo()
            val mRandomNumber = Math.random() * (mInfoMap.size - 2)
            val mIndex = mRandomNumber.roundToInt()
            val mUrl = "https://engine.presearch.org/search?q=${mInfoMap[mIndex]}"
            LogUtils.e("Presearch 第 $count 次请求链接 $mUrl")
            val cookiesMap = HashMap<String, String>()
            cookiesMap["Cookie"] = "remember_web_59ba36addc2b2f9401580f014c7f58ea4e30989d=eyJpdiI6IlpWdHVDZ25hUkE4Ym9yeFJvXC9UK25BPT0iLCJ2YWx1ZSI6Ik84dG9jSEU2ME0rWFhyaE5uZnRET3BmVTI2UVJMR0R2SGFtdmx0WlE0MjBWY3VCZkNrZ21pRVZDRDZHTDdnXC9kVkMxa0RmRXpFWm5OaU5zQmJCOWJ5bk9QK3E4bFdSRHJxbzBFZEYyT1BscVk5bTd2MVVkM1NJWTJTcnZzSks0eEk3d0ErYjRZbmpTS2pRRnRxbDE5VThhaURyOHdzMGpUSkg2cHZHSGhPVDZZQ2NSVzQrY2ZyS2orZDhaTm9ibnYiLCJtYWMiOiIxZGQ0M2Q0NzI3NDk0ZTQ2MDQ4NTg1N2NjMzQ5NjU2YTJhOWVjMzkwNGRkNTliM2Y3NDcwNGQ3MTgyOGZlMjI5In0%3D; __zlcmid=12VjmtCT0II3FRh; XSRF-TOKEN=eyJpdiI6InE1Z041MjBMWUM3UlpzQUNkMjBMaHc9PSIsInZhbHVlIjoiWHI1UGN6d2NkZkcwT0RXalhKZURSQ1wvSDgyYlQ0Wmh0dnpvazlnOFk5dTNvUzJsYlprTlNkMUljWkdTTVFnZ2MiLCJtYWMiOiIwYWYyYWI5YjcxMzBiZGY4NGY0MjQzMzU5NWZhMWQ2YjcwYTI4YTBhZDJjMzY1NTZmMGJhOTZmZWIwYTYyMmRiIn0%3D; presearch_session=eyJpdiI6IjVHYUgyRmNGblpWbGVLeTg2bjdObkE9PSIsInZhbHVlIjoiOEM3UVFSVFc5NVhGQlEyTXRZREFYclV0b2hrWWFBdFVhZzB3Snl2WlZSMWVaa2hzQ1wvM0huNE1BZFBuTVNMbHQiLCJtYWMiOiJiM2E0OThhM2EwYjU4ODkxZTEzZmUzMzY5NDg1Zjk2YjUwZWQyMzBiMDE4YjZkMjAwZTg1MjM4ODgzMGI1MTRjIn0%3D; token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE2MTI1MjAzOTEsImV4cCI6MTYxMzEyNTE5MSwidXNlcl9pZCI6MjA3NTI1MSwidXNlcl9hcGlfa2V5IjoiblBhcFloQ2VJYWdNUkN0OENKVDZ5cEFHeGpKbnBFTmJneUo4TTBVZnRuSXZWaHZ4MXJBeEpkUk9FRFZldUlrTmJnRTNET3NXTTBkcE5aN3pxU3NDRzc5SldhSGFZUHJVYjBaUmV2Sng3YmY2bVZ0SHdHanRYVlk3V1NHSTI5Zk1Fek53a0tNY0xEM1laOVkyMHBvRTZZIiwiYmV0YXMiOltdfQ.fcvkU-W9lHSBI9JHE1j7Hkdgv32SX5KCGTwg68BU6jwOIBFeLnth2NGTfk6Kh_fpuSokVtLcHS7JgTj64Nh8t7gGB0DgI99lvUXsJgzKJMRQO2pujKfgBO2uvaR-iF9SXdGPRZXjH65MpxHhqwLugn4Rxc2tJk5WPmVV4wm9mu3_BXzAL9G7fQzWQEoDeLeQ6p4PPl0OSa0RM6hKzwZL0tZijKb0g-ZB6LgB46j8P1rkmb2zm8O1-y0A4ZhMDDMtUs55TV1fSE03pCR1ZuQGA5IBOztwvg0fCd2GCZbmUeYEoWzF-8XCUSrIfwczjQvpWdp1Tvwc_I7k4bG2a1CQyw"
            var document = Jsoup.connect(mUrl).cookies(cookiesMap).get()
            LogUtils.e("第 $count 次请求结果", document)
            delay(3000)
            count--
            if (count < 0) {
                LogUtils.e("Presearch 退出")
                return@launchOnIO
            }
            requestWeb()
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

