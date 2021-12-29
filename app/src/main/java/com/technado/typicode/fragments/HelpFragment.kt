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
import com.technado.typicode.databinding.HelpFragmentBinding
import com.technado.typicode.helper.Titlebar

class HelpFragment : BaseFragment() {
    var binding: HelpFragmentBinding? = null

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setBackTitle(getActivityContext!!, "Help")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivityContext()?.lockMenu()
        getActivityContext?.hideBttomBar()
        getActivityContext?.lockMenu()

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_help, container, false)

        return binding?.root
    }
}