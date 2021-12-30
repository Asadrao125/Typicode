package com.technado.typicode.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technado.typicode.R
import com.technado.typicode.adapters.TodoAdapter
import com.technado.typicode.adapters.UserAdapter
import com.technado.typicode.base.BaseFragment
import com.technado.typicode.databinding.HomeFragmentBinding
import com.technado.typicode.databinding.TodoFragmentBinding
import com.technado.typicode.helper.Titlebar
import com.technado.typicode.models.ToDoModel
import com.technado.typicode.models.UserModel
import com.technado.typicode.viewModels.TodoViewModel
import com.technado.typicode.viewModels.UserViewModel

class TodoFragment : BaseFragment() {
    var binding: TodoFragmentBinding? = null
    lateinit var recyclerViewTodo: RecyclerView
    lateinit var viewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_todo, container, false)

        recyclerViewTodo = binding?.recyclerViewTodo!!
        viewModel = ViewModelProvider(this).get(TodoViewModel::class.java)
        recyclerViewTodo.layoutManager = LinearLayoutManager(getActivityContext)
        recyclerViewTodo.setHasFixedSize(true)

        viewModel.getToDoList().observe(getActivityContext!!, Observer<List<ToDoModel>?> {
            if (it != null) {
                recyclerViewTodo.adapter = TodoAdapter(getActivityContext!!, it)
            }
        })
        viewModel.getToDoList()

        return binding!!.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setBackTitle(getActivityContext!!, "Todo List")
    }
}