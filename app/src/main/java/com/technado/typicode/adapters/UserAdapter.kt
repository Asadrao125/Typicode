package com.technado.typicode.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technado.typicode.R
import com.technado.typicode.models.UserModel

class UserAdapter(var context: Context, var list: List<UserModel>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvUserName.text = list[position].name
        holder.tvCircle.text = list[position].name?.get(0).toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUserName: TextView
        var tvCircle: TextView

        init {
            tvUserName = itemView.findViewById(R.id.tvUserName)
            tvCircle = itemView.findViewById(R.id.tvCircle)
        }
    }
}