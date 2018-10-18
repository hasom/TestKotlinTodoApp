package com.hasom.firstkotlinapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hasom.firstkotlinapp.R
import com.hasom.firstkotlinapp.repository.TodoDB
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var todoDB : TodoDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id:Long = intent.getLongExtra("id", 0)

        todoDB = TodoDB.getInstance(this)

        val addRunnable = Runnable {
            var todo = todoDB?.todoDao()?.getTodo(id)

            tvTitle.text = todo?.title
            tvDesc.text = todo?.desc
        }
        val addThread = Thread(addRunnable)
        addThread.start()

    }
}