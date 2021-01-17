package com.oyz.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O ){
            val channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        sendNotice.setOnClickListener {
            val notification = NotificationCompat.Builder(this, "normal")
                    .setContentTitle("This is content title")
                    .setContentText("This is content text")
                    .setSmallIcon(R.drawable.small_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large_icon))
                    .build()
            manager.notify(1, notification)
        }


    }
}