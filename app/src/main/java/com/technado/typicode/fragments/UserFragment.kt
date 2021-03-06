package com.technado.typicode.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.technado.typicode.R
import com.technado.typicode.adapters.UserAdapter
import com.technado.typicode.base.BaseFragment
import com.technado.typicode.databinding.HomeFragmentBinding
import com.technado.typicode.helper.Dialog_CustomProgress
import com.technado.typicode.helper.RecyclerItemClickListener
import com.technado.typicode.helper.Titlebar
import com.technado.typicode.models.UserModel
import com.technado.typicode.viewModels.UserViewModel

class UserFragment : BaseFragment() {
    var binding: HomeFragmentBinding? = null
    lateinit var viewModel: UserViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var usersList: List<UserModel>
    lateinit var dialog: Dialog_CustomProgress

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)

        dialog = Dialog_CustomProgress(getActivityContext!!)
        recyclerView = binding?.recyclerViewUsers!!
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        recyclerView.layoutManager = LinearLayoutManager(getActivityContext)
        recyclerView.setHasFixedSize(true)

        val pDialog = SweetAlertDialog(getActivityContext, SweetAlertDialog.SUCCESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#000000")
        pDialog.titleText = "Loading..."
        pDialog.setCancelable(false)
        //pDialog.show()

        dialog.showProgressDialog()
        viewModel.getAllUsers().observe(getActivityContext!!, Observer<List<UserModel>?> {
            dialog.dismissProgressDialog()
            //pDialog.dismiss()
            if (it != null) {
                recyclerView.adapter = UserAdapter(getActivityContext!!, it)
                usersList = it
            }
            dialog.dismissProgressDialog()
        })
        viewModel.getAllUsers()

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                getActivityContext,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        getActivityContext!!.replaceFragment(
                            PostsFragment(usersList.get(position).name.toString()),
                            PostsFragment::class.java.simpleName,
                            true,
                            true
                        )
                    }

                    override fun onItemLongClick(view: View?, position: Int) {

                    }
                })
        )

        binding?.btnTodoList?.setOnClickListener(View.OnClickListener {
            getActivityContext!!.replaceFragment(
                TodoFragment(),
                TodoFragment::class.java.simpleName,
                true,
                false
            )
        })

        binding?.btnPhotos?.setOnClickListener(View.OnClickListener {
            getActivityContext!!.replaceFragment(
                PhotosFragment(),
                PhotosFragment::class.java.simpleName,
                true,
                false
            )
        })
        return binding?.root
    }

    override fun setTitlebar(titlebar: Titlebar) {
        titlebar.setTitle(getActivityContext!!, "Home - All Users")
    }
}