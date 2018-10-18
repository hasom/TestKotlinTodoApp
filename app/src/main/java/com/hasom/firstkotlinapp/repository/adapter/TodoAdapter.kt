package com.hasom.firstkotlinapp.repository.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import com.hasom.firstkotlinapp.R
import com.hasom.firstkotlinapp.repository.TodoDB
import com.hasom.firstkotlinapp.repository.entity.Todo


class TodoAdapter(val context: Context, val todoList: List<Todo>, val clickListener: (Todo) -> Unit) :
    Adapter<TodoAdapter.Holder>() {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(todoList[position], clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() : Int {
        return todoList.size
    }



    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView?.findViewById<TextView>(R.id.tvTitle)


        fun bind(todo: Todo, clickListener: (Todo) -> Unit) {
            tvTitle?.text = todo.title

            itemView.setOnClickListener { clickListener(todo)}

        }
    }
}