package com.hasom.firstkotlinapp.view.add

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hasom.firstkotlinapp.R
import com.hasom.firstkotlinapp.repository.TodoDB
import com.hasom.firstkotlinapp.repository.entity.Todo
import com.hasom.firstkotlinapp.utils.GlobalUtils
import com.hasom.firstkotlinapp.view.MainActivity
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    private var todoDB : TodoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        todoDB = TodoDB.getInstance(this)

        val addRunnable = Runnable {
            val newTodo = Todo()
            newTodo.title = etTitle.text.toString()
            newTodo.desc = etDesc.text.toString()
            newTodo.reg_date = GlobalUtils.dateFormat("yyyy-MM-dd HH:mm:ss")
            newTodo.mod_date = newTodo.reg_date

            todoDB?.todoDao()?.insert(newTodo)
        }

        fab.setOnClickListener {
            val addThread = Thread(addRunnable)
            addThread.start()
            finish()
        }

    }

    override fun onDestroy() {
        TodoDB.destoryINSTANCE()
        super.onDestroy()
    }
}