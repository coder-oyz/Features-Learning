package com.oyz.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    //用于读取保存的值
    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved",0)

        //viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(countReserved))
            .get(MainViewModel::class.java)

        //加一
        plusOneBtn.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }

        //清零
        clearBtn.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }

        refreshCounter()

        //加入生命周期的监听
        lifecycle.addObserver(MyObserver(lifecycle))
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved",viewModel.counter)
        }
    }

    private fun refreshCounter() {
        infoText.text = viewModel.counter.toString()
    }
}