package com.oyz.networktest

import android.media.session.MediaController
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

//测试
fun main() {
    val address = "http://www.baidu.com"
    HttpUtil.sendHttpRequest(address,object: HttpCallbackListener{
        override fun onFinish(response: String) {
            //处理响应数据
        }

        override fun onError(e: Exception) {
            //处理错误
        }

    } )

    //OkHttp
    HttpUtil.sendOkHttpRequest(address, object : Callback{
        override fun onFailure(call: Call, e: IOException) {
            //处理错误
        }

        override fun onResponse(call: Call, response: Response) {
            //获取响应数据
            val responseData = response.body?.string()
        }

    })
}