package com.hasom.firstkotlinapp.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.hasom.firstkotlinapp.repository.dao.TodoDao
import com.hasom.firstkotlinapp.repository.entity.Todo

@Database(entities = [(Todo::class)], version = 1, exportSchema = false)
abstract class TodoDB : RoomDatabase() {

    abstract fun todoDao() : TodoDao

    companion object {
        private var INSTANCE: TodoDB? = null

        fun getInstance(context: Context): TodoDB? {

            if (INSTANCE == null) {
                synchronized(TodoDB::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            TodoDB::class.java, "todo.db")
                            .fallbackToDestructiveMigration()
                            .build()
                }

            }

            return INSTANCE
        }

        fun destoryINSTANCE() {
            INSTANCE = null
        }
    }
}


