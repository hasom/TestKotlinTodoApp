package com.hasom.firstkotlinapp.repository.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo")
class Todo(
        @PrimaryKey(autoGenerate = true)
        var id:Long?,

        @ColumnInfo(name = "title")
        var title:String,

        @ColumnInfo(name = "desc")
        var desc:String,

        @ColumnInfo(name = "reg_date")
        var reg_date:String,

        @ColumnInfo(name = "mod_date")
        var mod_date:String

) {
    constructor(): this(null,"테스트", "테스트","20180920", "20180920")
}

