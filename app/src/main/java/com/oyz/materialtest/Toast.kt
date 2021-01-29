package com.example.materialtest

import android.content.Context
import android.widget.Toast
import com.oyz.materialtest.MyApplication

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    //Toast.makeText(context, this, duration).show()
    //直接全局获取context
    Toast.makeText(MyApplication.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    //Toast.makeText(context, this, duration).show()
    Toast.makeText(MyApplication.context, this, duration).show()
}