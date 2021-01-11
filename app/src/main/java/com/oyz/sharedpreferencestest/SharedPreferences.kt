package com.oyz.sharedpreferencestest

import android.content.SharedPreferences
//扩展函数的方法添加一个open函数，接收一个函数参数
fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    //调用Lambda
    editor.block()
    editor.apply()
}