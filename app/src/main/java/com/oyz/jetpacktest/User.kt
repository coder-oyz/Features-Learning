package com.oyz.jetpacktest

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 定义实体
 */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {
    //主键，自动生成
    @PrimaryKey(autoGenerate = true)
    var id: Long =0
}