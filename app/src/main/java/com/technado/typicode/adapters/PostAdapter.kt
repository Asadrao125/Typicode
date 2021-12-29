package com.technado.typicode.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technado.typicode.R
import com.technado.typicode.models.PostModel
import com.technado.typicode.models.UserModel

class PostAdapter(var context: Context, var list: List<PostModel>) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = list[position].body

        holder.tvComments.setOnClickListener(View.OnClickListener {

        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvComments: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvComments = itemView.findViewById(R.id.tvComments)
        }
    }
}