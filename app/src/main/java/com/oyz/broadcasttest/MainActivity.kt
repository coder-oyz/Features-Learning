package com.oyz.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 注册BroadcastReceiver的方式一般有两种：在代码中注册和在AndroidManifest.xml中注册。
 * 其中前者也被称为动态注册，后者也被称为静态注册。
 */
class MainActivity : AppCompatActivity() {
    //动态注册监听时间变化
    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //增加点击事件通过点击发送广播
        button.setOnClickListener {
            //创建一个intent，把要发送的广播是传送进去
            val intent = Intent("com.oyz.broadcasttest.MY_BROADCAST")
            //通过setPackage让他变成显示广播
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }


        val intentFilter = IntentFilter()
        //android.intent.action.TIME_TICK 监听时间变化
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        //记住动态注册的一定要关掉
        unregisterReceiver(timeChangeReceiver)
    }
    //定义内部类 继承BroadcastReceiver
    inner class TimeChangeReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show()
        }

    }

}
