package com.technado.typicode.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technado.typicode.R
import com.technado.typicode.models.CommentModel
import com.technado.typicode.models.PostModel
import com.technado.typicode.models.UserModel

class CommentAdapter(var context: Context, var list: List<CommentModel>) :
    RecyclerView.Adapter<CommentAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_comments, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvComment.text = list[position].body
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvComment: TextView

        init {
            tvComment = itemView.findViewById(R.id.tvComment)
        }
    }
}