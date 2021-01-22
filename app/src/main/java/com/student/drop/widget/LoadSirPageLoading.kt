package com.student.drop.widget

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.student.drop.R

class LoadSirPageLoading : Callback() {
    override fun onCreateView(): Int = R.layout.layout_page_loading

    //返回true 表示点击重试无效
    override fun onReloadEvent(context: Context?, view: View?): Boolean = true
}