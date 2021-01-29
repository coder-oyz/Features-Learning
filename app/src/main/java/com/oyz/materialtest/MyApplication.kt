package com.oyz.materialtest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication: Application() {
    /**
     * 以静态变量形式获取
     */
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}