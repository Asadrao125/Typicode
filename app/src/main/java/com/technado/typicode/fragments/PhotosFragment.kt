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
import com.technado.typicode.helper.Titlebar
import com.technado.typicode.models.PhotosModel
import com.technado.typicode.viewModels.PhotosViewModel

class PhotosFragment : BaseFragment() {
    var binding: PhotosFragmentBinding? = null
    lateinit var viewModel: PhotosViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var dialog: Dialog_CustomProgress

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