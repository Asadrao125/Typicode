package com.technado.typicode.helper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import com.technado.typicode.activities.HomeActivity
import com.technado.typicode.R
import com.technado.typicode.databinding.Titlebarbinding

class Titlebar : RelativeLayout {
    var binding: Titlebarbinding? = null

    constructor(context: Context) : super(context) {
        initLayout(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initLayout(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initLayout(context)
    }

    fun initLayout(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.titlebar, this, true)
    }

    fun hideTitleBar() {
        resetTitlebar()
    }

    fun resetTitlebar() {
        binding?.rlTitlebarMainLayout?.setVisibility(View.GONE)
    }

    fun setTitle(getActivityContext: HomeActivity, title: String) {
        binding?.rlTitlebarMainLayout?.setVisibility(View.VISIBLE)
        binding?.tvTitle?.text = title
        binding?.ivBack?.visibility = View.GONE

        binding?.ivBack?.setOnClickListener {
            getActivityContext.onBackPressed()
        }
    }

    fun setBackTitle(getActivityContext: HomeActivity, title: String) {
        binding?.rlTitlebarMainLayout?.setVisibility(View.VISIBLE)
        binding?.tvTitle?.text = title
        binding?.ivBack?.visibility = View.VISIBLE

        binding?.ivBack?.setOnClickListener {
            getActivityContext.onBackPressed()
        }
    }

    fun setHideTitle() {
        resetTitlebar()
        binding?.rlTitlebarMainLayout?.setVisibility(View.VISIBLE)
        binding?.ivBack?.visibility = View.INVISIBLE
    }
}