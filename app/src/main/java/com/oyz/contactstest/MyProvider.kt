package com.oyz.contactstest

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
//自定义ContentProvider
class MyProvider : ContentProvider() {

    private val table1Dir = 0
    private val table1Item = 1
    private val table2Dir = 2
    private val table2Item = 3
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI("com.oyz.app.provider", "table1", table1Dir)
        uriMatcher.addURI("com.oyz.app.provider ", "table1/#", table1Item)
        uriMatcher.addURI("com.oyz.app.provider ", "table2", table2Dir)
        uriMatcher.addURI("com.oyz.app.provider ", "table2/#", table2Item)
    }


    //完成数据库的创建和升级
    override fun onCreate(): Boolean {
        return false
    }

    //用于查询
    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        when (uriMatcher.match(uri)) {
            table1Dir -> {
                // 查询table1表中的所有数据
            }
            table1Item -> {
                // 查询table1表中的单条数据
            }
            table2Dir -> {
                // 查询table2表中的所有数据
            }
            table2Item -> {
                // 查询table2表中的单条数据
            }
        }
        return null
    }

    //用于插入
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    //用于更新
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    //用于删除
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    //返回URI相应的MIME类型
    override fun getType(uri: Uri) = when(uriMatcher.match(uri)){
        table1Dir -> "vnd.android.cursor.dir/vnd.com.example.app.provider.table1"
        table1Item -> "vnd.android.cursor.item/vnd.com.example.app.provider.table1"
        table2Dir -> "vnd.android.cursor.dir/vnd.com.example.app.provider.table2"
        table2Item -> "vnd.android.cursor.item/vnd.com.example.app.provider.table2"
        else -> null
    }

}
