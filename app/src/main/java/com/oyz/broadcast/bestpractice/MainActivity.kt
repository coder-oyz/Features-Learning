package com.oyz.broadcast.bestpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //发送下线广播
        forceOffline.setOnClickListener {
            Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()
            val intent = Intent("com.oyz.broadcast.bestpractice.FORCE_OFFLINE")
            sendBroadcast(intent)
        }
    }
}