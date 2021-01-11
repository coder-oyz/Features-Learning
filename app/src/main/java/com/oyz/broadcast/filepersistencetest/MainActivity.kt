package com.oyz.broadcast.filepersistencetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //从文件中读取数据
        val inputText = load()
        if(inputText.isNotEmpty()) {
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show()
        }
    }
    //Context类中还提供了一个openFileInput()方法，用于从文件中读取数据。
    //它会自动到/data/data/<package name>/files/目录下加载文件，并返回一个FileInputStream对象，得到这个对象之后，再通过流的方式就可以将数据读取出来了
    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                //forEachLine 把读到的每一行内容放到Lambda表达式中
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
        //不能再try块中return
        return content.toString()
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