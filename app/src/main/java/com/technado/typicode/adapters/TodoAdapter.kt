package com.technado.typicode.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technado.typicode.R
import com.technado.typicode.models.CommentModel
import com.technado.typicode.models.PostModel
import com.technado.typicode.models.ToDoModel
import com.technado.typicode.models.UserModel

class TodoAdapter(var context: Context, var list: List<ToDoModel>) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = list[position].title

        if (list.get(position).completed) {
            holder.cbCompleted.isChecked = true
        } else {
            holder.cbCompleted.isChecked = false
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var cbCompleted: CheckBox

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            cbCompleted = itemView.findViewById(R.id.cbCompleted)
        }
    }
}