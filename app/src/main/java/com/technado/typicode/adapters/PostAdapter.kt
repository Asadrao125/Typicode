package com.technado.typicode.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.technado.typicode.R
import com.technado.typicode.databinding.ItemPostBinding
import com.technado.typicode.databinding.PhotosFragmentBinding
import com.technado.typicode.models.PostModel
import com.technado.typicode.models.UserModel

class PostAdapter(var context: Context, var list: List<PostModel>) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {
    var binding: ItemPostBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //val view: View = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_post, parent, false)
        return MyViewHolder(binding!!.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding?.tvTitle?.text = list[position].body

        binding?.tvComments?.setOnClickListener(View.OnClickListener {

        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*var tvTitle: TextView
        var tvComments: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvComments = itemView.findViewById(R.id.tvComments)
        }*/
    }
}