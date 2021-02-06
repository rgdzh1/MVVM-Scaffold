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
    companion object {
        const val COOKE = "cookies_my"
    }

    private var mHandlerInfo = mutableListOf<String>()
    var mCount = 10

    /**
     * 开始刷
     */
    fun startRefresh(cookies: String, count: Int) {
        if (!cookies.isNullOrBlank()) {
            MySPUtils.encodeString(COOKE, cookies)
        } else {
            if (MySPUtils.decodeString(COOKE).isNullOrBlank()) {
                ToastUtils.showLong("Cookies不能为空")
                return
            }
        }
        mRepo.mPageStateMLD.postValue(MyPageState(isDialogLoding = true))
        if (count <= 0) {
            mCount = 10
        } else {
            mCount = count
        }
        processWork()
    }

    /**
     * 执行
     */
    private fun processWork() {
        launchOnIO {
            val mInfoMap = getSearchInfo()
            val mRandomNumber = Math.random() * (mInfoMap.size - 2)
            val mIndex = mRandomNumber.roundToInt()
            val mUrl = "https://engine.presearch.org/search?q=${mInfoMap[mIndex]}"
            // LogUtils.e("Presearch 第 $mCount 次请求链接 $mUrl")
            val cookiesMap = HashMap<String, String>()
            cookiesMap["Cookie"] = MySPUtils.decodeString(COOKE)
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

