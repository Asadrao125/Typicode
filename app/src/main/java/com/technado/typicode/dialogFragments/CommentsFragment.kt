package com.technado.typicode.dialogFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.technado.typicode.R
import com.technado.typicode.adapters.CommentAdapter
import com.technado.typicode.adapters.PostAdapter
import com.technado.typicode.base.BaseFragment
import com.technado.typicode.databinding.AboutFragmentBinding
import com.technado.typicode.databinding.CommentsFragmentBinding
import com.technado.typicode.helper.Titlebar
import com.technado.typicode.models.CommentModel
import com.technado.typicode.models.PostModel
import com.technado.typicode.viewModels.CommentViewModel
import com.technado.typicode.viewModels.PostViewModel

class CommentsFragment : BaseFragment() {
    var binding: CommentsFragmentBinding? = null
    lateinit var viewModel: CommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_comments, container, false)
        viewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        binding?.recyclerViewComments?.layoutManager = LinearLayoutManager(getActivityContext)
        binding?.recyclerViewComments?.setHasFixedSize(true)

        viewModel.getAllComments().observe(getActivityContext!!, Observer<List<CommentModel>?> {
            if (it != null) {
                binding?.recyclerViewComments?.adapter = CommentAdapter(getActivityContext!!, it)
            }
        })
        viewModel.getAllComments()

        return binding?.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setTitle(getActivityContext!!, "Comments")
    }
}