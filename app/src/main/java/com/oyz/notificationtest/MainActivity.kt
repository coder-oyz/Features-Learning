package com.oyz.notificationtest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
            //通知一经创建，不能修改重要等级
            val channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)

            val channe2 = NotificationChannel("important","Important",NotificationManager.IMPORTANCE_HIGH)
            //manager.createNotificationChannel(channel)
            manager.createNotificationChannel(channe2)
        }

        sendNotice.setOnClickListener {
            //布置通知的点击
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0 ,intent, 0)

            val notification = NotificationCompat.Builder(this, "important")
                    .setContentTitle("This is content title")
                    .setContentText("This is content text")
                    //设置大段文字   显示不下，会缩略
                    //.setContentText("Learn how to build notifications, send and sync  data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android.")
                    //通过style设置bigText
                    //.setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync  data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                    //显示大图
                    .setStyle(NotificationCompat.BigPictureStyle().bigPicture(
                        BitmapFactory.decodeResource(resources, R.drawable.big_image)))
                    .setSmallIcon(R.drawable.small_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large_icon))
                    //设置点击后的页面
                    .setContentIntent(pi)
                    //设置自动取消
                    .setAutoCancel(true)
                    .build()
            manager.notify(1, notification)
        }


    }
}