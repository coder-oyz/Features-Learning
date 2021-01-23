package com.oyz.materialtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        //增加导航按钮
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        //call 默认选中
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers() //关闭滑动菜单
            true
        }

        fab.setOnClickListener {
            //Toast.makeText(this, "FAB clicked", Toast.LENGTH_SHORT).show()

            //Snackbar的使用   与Toast相比只是增加了一个按钮的点击事件
            view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo"){
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)  //导航按钮   开启滑动窗口

            R.id.backup -> Toast.makeText(this, "You clicked BackUp", Toast.LENGTH_SHORT).show()

            R.id.delete -> Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show()

            R.id.settings -> Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }

}