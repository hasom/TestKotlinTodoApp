package com.hasom.firstkotlinapp.view.update

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import com.hasom.firstkotlinapp.R
import com.hasom.firstkotlinapp.repository.TodoDB
import com.hasom.firstkotlinapp.repository.entity.Todo
import com.hasom.firstkotlinapp.utils.GlobalUtils
import com.hasom.firstkotlinapp.view.MainActivity
import kotlinx.android.synthetic.main.activity_add.*

class UpdateActivity : AppCompatActivity() {

    private var todoDB : TodoDB? = null
    var id:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        id = intent.getLongExtra("id", 0)

        todoDB = TodoDB.getInstance(this)

        val detailRunnable = Runnable {
            var todo = todoDB?.todoDao()?.getTodo(id)

            etTitle.text = Editable.Factory.getInstance().newEditable(todo?.title)
            etDesc.text = Editable.Factory.getInstance().newEditable(todo?.desc)
        }

        val detailThread = Thread(detailRunnable)
        detailThread.start()

        val updateRunnable = Runnable {
            val updateTodo = Todo()
            updateTodo.id = id
            updateTodo.title = etTitle.text.toString()
            updateTodo.desc = etDesc.text.toString()
            updateTodo.mod_date = GlobalUtils.dateFormat("yyyy-MM-dd HH:mm:ss")

            todoDB?.todoDao()?.updateTodo(updateTodo)

            setResult(Activity.RESULT_OK)
            finish()
        }

        fab.setOnClickListener {
            val updateThread = Thread(updateRunnable)
            updateThread.start()

        }

    }

    override fun onDestroy() {
        TodoDB.destoryINSTANCE()
        super.onDestroy()
    }
}