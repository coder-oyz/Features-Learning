package com.oyz.databasetest

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.lang.NullPointerException

/**
 * 添加
 * db.execSQL(
“insert into Book (name, author, pages, price) values(?, ?, ?, ?)”, arrayOf("The Da Vinci Code", "Dan Brown", "454", "16.96")
)
更新
db.execSQL("update Book set price = ? where name = ?", arrayOf("10.99", "The Da Vinci Code"))
删除
db.execSQL("delete from Book where pages > ?", arrayOf("500"))
查询
val cursor = db.rawQuery("select * from Book", null)

 */
class MainActivity : AppCompatActivity() {

    var bookId: String?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //想调用数据库的升级方法，就必须把version提升
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 3)
        createDatabase.setOnClickListener {
            //获取写数据库对象
            dbHelper.writableDatabase
        }

        //添加数据
        addData.setOnClickListener {
            // 添加数据
            val uri = Uri.parse("content://com.oyz.databasetest.provider/book")
            val values = contentValuesOf("name" to "A Clash of Kings", "author" to "George Martin", "pages" to 1040, "price" to 22.85)
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }


        /*addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入一条数据

            val values2 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2) // 插入一条数据

        }*/

        //更新数据

        updateData.setOnClickListener {
            // 更新数据
            bookId?.let {
                val uri = Uri.parse("content://com.oyz.databasetest.provider/book/$it")
                val values = contentValuesOf("name" to "A Storm of Swords", "pages" to 1216, "price" to 24.05)
                contentResolver.update(uri, values, null, null)
            }
        }

        /*updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            //The Da Vinci Code这本书 价格变成10.99
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))

        }*/

        //删除数据
        deleteData.setOnClickListener {
            // 删除数据
            bookId?.let {
                val uri = Uri.parse("content://com.oyz.databasetest.provider/book/$it")
                contentResolver.delete(uri, null, null)
            }
        }

        /*deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))

        }*/

        //查询数据
        queryData.setOnClickListener {
            // 查询数据
            val uri = Uri.parse("content://com.oyz.databasetest.provider/book")
            contentResolver.query(uri, null, null, null, null)?.apply {
                while (moveToNext()) {
                    val name = getString(getColumnIndex("name"))
                    val author = getString(getColumnIndex("author"))
                    val pages = getInt(getColumnIndex("pages"))
                    val price = getDouble(getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                }
                close()
            }
        }

        //七个参数
        //table	from table_name	指定查询的表名
        //columns	select column1, column2	指定查询的列名
        //selection	where column = value	指定where的约束条件
        //selectionArgs	-	为where中的占位符提供具体的值
        //groupBy	group by column	指定需要group by的列
        //having	having column = value	对group by后的结果进一步约束
        //orderBy	order by column1, column2	指定查询结果的排序方式
        /*queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查询Book表中所有的数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    // 遍历Cursor对象，取出数据并打印
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()

        }*/
        //事务测试
        replaceData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction() //开启事务
            try {
                db.delete("Book",null,null)
                if (true) {
                    //手动抛出一个异常，让事务失败
                    throw NullPointerException()
                }
                /*val values = ContentValues().apply {
                    // 开始组装第一条数据
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.95)
                }*/
                //自己写的
                val values = cvOf("name" to "Game of Thrones", "author" to "George Martin", "pages" to 720, "price" to 20.85)
                //Kotlin提供的优化写法
                //val values = contentValuesOf("name" to "Game of Thrones", "author" to "George Martin", "pages" to 720, "price" to 20.85)
                db.insert("Book", null, values) // 插入一条数据
                db.endTransaction()  //事务执行成功
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                db.endTransaction()  //结束事务
            }
        }

    }
}