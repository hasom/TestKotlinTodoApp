package com.hasom.firstkotlinapp.view

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.hasom.firstkotlinapp.R
import com.hasom.firstkotlinapp.repository.TodoDB
import com.hasom.firstkotlinapp.repository.adapter.TodoAdapter
import com.hasom.firstkotlinapp.repository.entity.Todo
import com.hasom.firstkotlinapp.view.add.AddActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration



class MainActivity : AppCompatActivity() {

    private var todoDB : TodoDB? = null
    private var todoList = listOf<Todo>()
    lateinit var mAdapter: TodoAdapter


    var r = Runnable {
        try {
            todoList = todoDB?.todoDao()?.getAll()!!
            System.out.println("count = " + todoList.size)

            this@MainActivity.runOnUiThread {
                mAdapter.setListData(todoList)
                mAdapter.notifyDataSetChanged()
            }


        } catch (e : Exception) {
            Log.e("tag", "Error - $e")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoDB = TodoDB.getInstance(this)

        mAdapter = TodoAdapter(this, todoList, { todo : Todo -> onItemClicked(todo) })

        listView.adapter = mAdapter
        listView.layoutManager = LinearLayoutManager(this)
        listView.setHasFixedSize(true)
        listView.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager(this).orientation))

        fab.setOnClickListener{
             val i = Intent(this, AddActivity::class.java)
             startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()
        val thread = Thread(r)
        thread.start()
    }

    private fun onItemClicked(todo : Todo) {
        Log.d("TAG", "id = " + todo.id)
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra("id", todo.id)
        startActivity(i)
    }

    override fun onDestroy() {
        TodoDB.destoryINSTANCE()
        todoDB = null
        super.onDestroy()
    }
}
