package com.oyz.jetpacktest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 用LiveData包装counter
 */
class MainViewModel(countReserved: Int) : ViewModel() {
    var counter = MutableLiveData<Int>()

    init {
        counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?:0
        counter.value = count + 1
    }

    fun clear() {
        counter.value = 0
    }
}

/**
 * 通过构造函数传入参数
 */
//class MainViewModel(countReserved: Int) : ViewModel() {
//    var counter = countReserved
//}

//class MainViewModel() : ViewModel() {
//    var counter = 0
//}