package com.hasom.firstkotlinapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.hasom.firstkotlinapp.R
import com.hasom.firstkotlinapp.repository.TodoDB
import com.hasom.firstkotlinapp.repository.entity.Todo
import com.hasom.firstkotlinapp.view.add.AddActivity
import com.hasom.firstkotlinapp.view.update.UpdateActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var todoDB : TodoDB? = null
    var id:Long = 0
    var isFabOpen:Boolean = false

    var fab_open:Animation? = null
    var fab_close:Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        id = intent.getLongExtra("id", 0)

        todoDB = TodoDB.getInstance(this)

        val detailRunnable = Runnable {
            var todo = todoDB?.todoDao()?.getTodo(id)

            tvTitle.text = todo?.title
            tvDesc.text = todo?.desc
        }
        val detailThread = Thread(detailRunnable)
        detailThread.start()

        fab.setOnClickListener {
            anim()
        }

        fab1.setOnClickListener {
            val i = Intent(this, UpdateActivity::class.java)
            i.putExtra("id", id)
            startActivityForResult(i, 10000)
        }

        fab2.setOnClickListener {

            val deleteRunnable = Runnable {
                val deleteTodo = Todo()
                deleteTodo.id = id
                todoDB?.todoDao()?.deleteTodo(deleteTodo)
                setResult(Activity.RESULT_OK)
                finish()
            }
            val deleteThread = Thread(deleteRunnable)
            deleteThread.start()

        }

        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 10000 && resultCode === Activity.RESULT_OK) {
            finish()
        }

    }

    fun anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close)
            fab2.startAnimation(fab_close)
            fab1.isClickable = false
            fab2.isClickable = false
            isFabOpen = false
            tvFab1.startAnimation(fab_close)
            tvFab2.startAnimation(fab_close)
        } else {
            fab1.startAnimation(fab_open)
            fab2.startAnimation(fab_open)
            fab1.isClickable = true
            fab2.isClickable = true
            isFabOpen = true
            tvFab1.startAnimation(fab_open)
            tvFab2.startAnimation(fab_open)
        }
    }
}