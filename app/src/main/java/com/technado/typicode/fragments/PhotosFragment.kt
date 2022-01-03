package com.technado.typicode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technado.typicode.R
import com.technado.typicode.adapters.PhotosAdapter
import com.technado.typicode.base.BaseFragment
import com.technado.typicode.databinding.PhotosFragmentBinding
import com.technado.typicode.helper.Dialog_CustomProgress
import com.technado.typicode.helper.RecyclerItemClickListener
import com.technado.typicode.helper.Titlebar
import com.technado.typicode.models.PhotosModel
import com.technado.typicode.viewModels.PhotosViewModel

class PhotosFragment : BaseFragment() {
    var binding: PhotosFragmentBinding? = null
    lateinit var viewModel: PhotosViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var dialog: Dialog_CustomProgress
    lateinit var photosList: List<PhotosModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)

        dialog = Dialog_CustomProgress(getActivityContext!!)
        recyclerView = binding?.recyclerViewPhotos!!
        recyclerView.layoutManager = LinearLayoutManager(getActivityContext)
        recyclerView.setHasFixedSize(true)
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)

        dialog.showProgressDialog()
        viewModel.getAllPictures().observe(getActivityContext!!, Observer<List<PhotosModel>?> {
            dialog.dismissProgressDialog()
            if (it != null) {
                photosList = it
                recyclerView.adapter = PhotosAdapter(getActivityContext!!, it)
            }
        })
        viewModel.getAllPictures()

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                getActivityContext,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        PostsFragment.postId = photosList.get(position).id.toString()
                        val url: String = photosList.get(position).url
                        getActivityContext!!.replaceFragment(
                            ImageViewFragment(url),
                            ImageViewFragment::class.java.simpleName,
                            true,
                            false
                        )
                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                    }
                })
        )

        return binding!!.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setBackTitle(getActivityContext!!, "Photos")
    }
}