package com.oyz.networktest

import java.lang.Exception

/**
 * 回调
 */
interface HttpCallbackListener {

    fun onFinish(response: String)
    fun onError(e: Exception)
}