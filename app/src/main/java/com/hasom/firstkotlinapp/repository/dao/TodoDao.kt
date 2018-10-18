package com.hasom.firstkotlinapp.repository.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.hasom.firstkotlinapp.repository.entity.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodo(id: Long) : Todo

    @Insert(onConflict = REPLACE)
    fun insert(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

}