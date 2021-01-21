package com.oyz.networktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendRequestBtn.setOnClickListener {
            sendRequestWithHttpURLConnection()
            //sendRequestWithOkHttp()
        }
    }

    /*
    //Post请求发送方法
    connection?.requestMethod = "POST"
    val output = DataOutputStream(connection?.outputStream)
    output.writeBytes("username=admin&password=123456")
    */
    private fun sendRequestWithHttpURLConnection() {
        // 开启线程来发起网络请求
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")

                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                // 下面对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        //异步机制，是的允许子线程能够改变UI
        runOnUiThread {
            // 在这里进行UI操作，将结果显示到界面上
            responseText.text = response
        }
    }

    /*private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                        // 指定访问的服务器地址是电脑本机
//                    .url("http://10.0.2.2/get_data.xml")
                        .url("http://10.0.2.2/get_data.json")
                        .build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if (responseData != null) {
//                    parseXMLWithPull(responseData)
//                    parseXMLWithSAX(responseData)
//                    parseJSONWithJSONObject(responseData)
                    parseJSONWithGSON(responseData)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }*/
}