package com.technado.typicode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.technado.typicode.R
import com.technado.typicode.adapters.CommentAdapter
import com.technado.typicode.adapters.PostAdapter
import com.technado.typicode.base.BaseFragment
import com.technado.typicode.databinding.AboutFragmentBinding
import com.technado.typicode.helper.Dialog_CustomProgress
import com.technado.typicode.helper.RecyclerItemClickListener
import com.technado.typicode.helper.Titlebar
import com.technado.typicode.models.CommentModel
import com.technado.typicode.models.PostModel
import com.technado.typicode.viewModels.CommentViewModel
import com.technado.typicode.viewModels.PostViewModel

class PostsFragment(var name: String) : BaseFragment() {
    var binding: AboutFragmentBinding? = null
    lateinit var viewModel: PostViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var postList: List<PostModel>
    lateinit var commentViewModel: CommentViewModel
    lateinit var dialogCustom: Dialog_CustomProgress

    companion object {
        var postId = "1"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts, container, false)

        dialogCustom = Dialog_CustomProgress(getActivityContext!!)
        recyclerView = binding?.recyclerViewPosts!!
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(getActivityContext)
        recyclerView.setHasFixedSize(true)

        dialogCustom.showProgressDialog()
        viewModel.getAllPosts().observe(getActivityContext!!, Observer<List<PostModel>?> {
            dialogCustom.dismissProgressDialog()
            if (it != null) {
                recyclerView.adapter = PostAdapter(getActivityContext!!, it)
                postList = it
            }
        })
        viewModel.getAllPosts()

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                getActivityContext,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        postId = postList.get(position).id.toString()
                        showCommentsDialog()
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                        Toast.makeText(
                            getActivityContext,
                            "" + postList.get(position).id.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        )
        return binding?.root
    }

    private fun showCommentsDialog() {
        val dialog = BottomSheetDialog(
            getActivityContext!!,
            R.style.CustomBottomSheetDialogTheme
        )
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

        val recyclerViewComments = view.findViewById<RecyclerView>(R.id.recyclerViewComments)

        val imgBack = view.findViewById<ImageView>(R.id.imgBack)
        val tvComments = view.findViewById<TextView>(R.id.tvComments)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        tvComments.text = "Comments of Post # ${postId}"

        recyclerViewComments?.layoutManager = LinearLayoutManager(getActivityContext)
        recyclerViewComments?.setHasFixedSize(true)

        progressBar.visibility = View.VISIBLE
        recyclerViewComments.visibility = View.GONE

        commentViewModel.getAllComments()
            .observe(getActivityContext!!, Observer<List<CommentModel>?> {

                progressBar.visibility = View.GONE
                recyclerViewComments.visibility = View.VISIBLE

                if (it != null) {
                    recyclerViewComments.adapter =
                        CommentAdapter(getActivityContext!!, it)
                }
            })
        commentViewModel.getAllComments()

        imgBack.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })

        dialog.setContentView(view)
        dialog.show()
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setBackTitle(getActivityContext!!, "Posts (${name})")
    }
}