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
import com.technado.typicode.adapters.PhotosAdapter
import com.technado.typicode.adapters.PostAdapter
import com.technado.typicode.base.BaseFragment
import com.technado.typicode.databinding.AboutFragmentBinding
import com.technado.typicode.databinding.PhotosFragmentBinding
import com.technado.typicode.helper.Titlebar
import com.technado.typicode.models.PhotosModel
import com.technado.typicode.models.PostModel
import com.technado.typicode.viewModels.PhotosViewModel
import com.technado.typicode.viewModels.PostViewModel

class PhotosFragment : BaseFragment() {
    var binding: PhotosFragmentBinding? = null
    lateinit var viewModel: PhotosViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false)


        recyclerView = binding?.recyclerViewPhotos!!
        recyclerView.layoutManager = LinearLayoutManager(getActivityContext)
        recyclerView.setHasFixedSize(true)

        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)

        viewModel.getAllPictures().observe(getActivityContext!!, Observer<List<PhotosModel>?> {
            if (it != null) {
                recyclerView.adapter = PhotosAdapter(getActivityContext!!, it)
            }
        })
        viewModel.getAllPictures()
        return binding!!.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setBackTitle(getActivityContext!!, "Photos")
    }
}