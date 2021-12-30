package com.technado.typicode.helper

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.technado.typicode.R

class Constants {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val USERS = "users"
        const val POSTS = "posts"
        const val COMMENTS = "comments?"
        const val TODO = "todos"
        const val PHOTOS = "photos"

        fun loadImage(imageUrl: String, imageView: ImageView) {
            Picasso.get().load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView)
        }
    }
}