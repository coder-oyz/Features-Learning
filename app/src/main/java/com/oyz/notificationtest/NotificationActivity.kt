package com.oyz.notificationtest

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //显示指定id取消
        manager.cancel(1)
    }
}