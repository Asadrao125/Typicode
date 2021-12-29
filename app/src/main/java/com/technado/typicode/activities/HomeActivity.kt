package com.technado.typicode.activities

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.technado.typicode.R
import com.technado.typicode.base.BaseActivity
import com.technado.typicode.databinding.HomeActivityBinding
import com.technado.typicode.dialogFragments.ExitDialog
import com.technado.typicode.fragments.UserFragment
import com.technado.typicode.helper.Titlebar

class HomeActivity : BaseActivity() {
    var binding: HomeActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setMainFrameLayoutID()

        replaceFragment(UserFragment(), UserFragment::class.java.simpleName, true, false)
    }

    fun getTitlebar(): Titlebar {
        return binding!!.titlebar
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            val fragmentManager = supportFragmentManager
            val fragments: List<Fragment> = fragmentManager.fragments
            val last: Fragment = fragments.get(fragments.size - 1)

            supportFragmentManager.popBackStack()

        } else {
            val exitDialog = ExitDialog()
            exitDialog.show(supportFragmentManager, "exitDialog")
        }
    }

    fun mainHideTitle() {
        binding!!.titlebar.visibility = View.GONE
    }

    fun mainShowTitle() {
        binding!!.titlebar.visibility = View.VISIBLE
    }

    fun clearBackStack() {
        val fragmentManager = supportFragmentManager
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
    }

    override fun setMainFrameLayoutID() {
        mainFrameLayoutID = binding?.mainContainer!!.id
    }
}