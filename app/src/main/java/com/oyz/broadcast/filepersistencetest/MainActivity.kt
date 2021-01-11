package com.oyz.broadcast.filepersistencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = editText.text.toString()
        //销毁时进行保存
        save(inputText)
    }

    //进行文件存储
    private fun save(inputText: String) {
        try {
            //MODE_PRIVATE 覆盖式写法
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            //use 函数 会保证Lambda执行完成后自动将流关闭
            writer.use {
                it.write(inputText)
            }
        }catch (e: IOException){
            e.printStackTrace()
        }

    }


}