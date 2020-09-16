package com.yey.plus.widget

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.yey.plus.R

class LoadSirPageNetErr : Callback() {
    override fun onCreateView(): Int = R.layout.layout_page_net_err

    //返回false 表示点击重试有效果
    override fun onReloadEvent(context: Context?, view: View?): Boolean = false
}