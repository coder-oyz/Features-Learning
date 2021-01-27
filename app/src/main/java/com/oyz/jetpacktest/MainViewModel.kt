package com.oyz.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 将原来的counter改为_counter，再加一个private修饰符，使对外不可见
 * 再加一个counter声明为不可变的LiveData,它的get属性方法返回_counter
 */
class MainViewModel(countReserved: Int) : ViewModel() {
    val counter: LiveData<Int> get() =_counter
    val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?:0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }
}


/**
 * 用LiveData包装counter（MutableLiveData 可变的数据）
 * 但是这样暴露数据给ViewModel外面，是的外面也可以对数据进行设值
 */
/*class MainViewModel(countReserved: Int) : ViewModel() {
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
}*/

/**
 * 通过构造函数传入参数
 */
//class MainViewModel(countReserved: Int) : ViewModel() {
//    var counter = countReserved
//}

//class MainViewModel() : ViewModel() {
//    var counter = 0
//}