package com.oyz.androidthreadtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

/**
 * Android的UI是线程不安全的，也就是说，如果想要更新应用程序里的UI元素，必须在主线程中进行，
 * 否则就会出现异常。对于这种情况，Android提供了一套异步消息处理机制，完美地解决了在子线程中进行UI操作的问题。
 */
class MainActivity : AppCompatActivity() {
    //使用异步消息处理机制
    val updateText = 1

    val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            // 在这里可以进行UI操作
            when (msg.what) {// 当msg.what 等于updateText时，执行变换
                updateText -> textView.text = "Nice to meet you"
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeTextBtn.setOnClickListener {
            //直接使用子线程更新是不行的，会出bug
            //Only the original thread that created a view hierarchy can touch its views
            /*thread {
                textView.text = "Nice to meet you"
            }*/

            //异步
            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)  //将Message对象发送出去
            }
        }
    }
}