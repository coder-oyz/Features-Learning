package com.oyz.databasetest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

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

        }

        //更新数据
        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            //The Da Vinci Code这本书 价格变成10.99
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))

        }

        //删除数据
        deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))

        }

        //查询数据
        //七个参数
        //table	from table_name	指定查询的表名
        //columns	select column1, column2	指定查询的列名
        //selection	where column = value	指定where的约束条件
        //selectionArgs	-	为where中的占位符提供具体的值
        //groupBy	group by column	指定需要group by的列
        //having	having column = value	对group by后的结果进一步约束
        //orderBy	order by column1, column2	指定查询结果的排序方式
        queryData.setOnClickListener {
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

        }

    }
}