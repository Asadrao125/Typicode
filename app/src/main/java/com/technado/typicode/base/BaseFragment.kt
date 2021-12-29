package com.technado.typicode.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.technado.typicode.activities.HomeActivity
import com.technado.typicode.helper.Titlebar

abstract class BaseFragment : Fragment() {
    var getActivityContext: HomeActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun setTitlebar(titlebar: Titlebar)

    fun getActivityContext(): HomeActivity? {
        return getActivityContext
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeActivity) {
            val contex = context as HomeActivity?
            if (contex != null)
                getActivityContext = context
        }
    }

    override fun onResume() {
        super.onResume()
        if (getActivityContext != null) {
            setTitlebar(getActivityContext!!.getTitlebar())
        }
    }
}