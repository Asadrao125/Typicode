package com.technado.typicode.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class ImageAnimation {
    companion object {
        fun animatedImageView(c: Context?, v: ImageView, new_image: Drawable?) {
            val anim_out: Animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_out)
            val anim_in: Animation = AnimationUtils.loadAnimation(c, android.R.anim.fade_in)
            anim_out.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    v.setImageDrawable(new_image)
                    anim_in.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationRepeat(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {}
                    })
                    v.startAnimation(anim_in)
                }
            })
            v.startAnimation(anim_out)
        }
    }
}