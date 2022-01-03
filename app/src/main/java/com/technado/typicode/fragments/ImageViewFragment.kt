package com.technado.typicode.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.technado.typicode.R
import com.technado.typicode.base.BaseFragment
import com.technado.typicode.databinding.AboutFragmentBinding
import com.technado.typicode.databinding.ImageViewFragmentBinding
import com.technado.typicode.helper.Constants
import com.technado.typicode.helper.Titlebar

class ImageViewFragment(var imageUrl: String) : BaseFragment() {
    var binding: ImageViewFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image_view, container, false)
        Constants.loadImage(imageUrl, binding?.imageView!!)
        return binding?.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setBackTitle(getActivityContext!!, "Image")
    }
}