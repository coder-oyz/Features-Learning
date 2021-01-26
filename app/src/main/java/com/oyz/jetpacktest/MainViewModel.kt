package com.oyz.jetpacktest

import androidx.lifecycle.ViewModel

/**
 * 通过构造函数传入参数
 */
class MainViewModel(countReserved: Int) : ViewModel() {
    var counter = countReserved
}

//class MainViewModel() : ViewModel() {
//    var counter = 0
//}