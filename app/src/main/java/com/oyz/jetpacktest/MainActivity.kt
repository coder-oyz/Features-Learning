package com.oyz.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

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
            /*viewModel.counter++
            refreshCounter()*/

            viewModel.plusOne()
        }

        //清零
        clearBtn.setOnClickListener {
            /*viewModel.counter = 0
            refreshCounter()*/

            viewModel.clear()
        }

        getUserBtn.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.user.observe(this) {user ->
            infoText.text = user.firstName
        }

        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        val userDao = AppDatabase.getDatabase(this).userDao()



        addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("MainActivity", user.toString())
                }
            }
        }
        doWorkBtn.setOnClickListener {
            //请求获取与传入WorkManager
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            WorkManager.getInstance(this).enqueue(request)
        }

        refreshCounter()

        //加入生命周期的监听
        lifecycle.addObserver(MyObserver(lifecycle))
    }

    /**
     * 不能使用,因为这个时候一直监听的是老的LiveData实例，这时候就要使用switchMap了
     * viewModel.counter.observe(this) {count ->
        infoText.text = count.toString()
        }
     */
    fun getUser(userId: String):LiveData<User> {
        return Repository.getUser(userId)
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved",viewModel.counter.value?:0)
        }
    }

    private fun refreshCounter() {
        //infoText.text = viewModel.counter.toString()
        //监听刷新数据
        /*viewModel.counter.observe(this, Observer { count ->
            infoText.text = count.toString()
        })*/
        viewModel.counter.observe(this) {count ->
            infoText.text = count.toString()
        }
    }
}