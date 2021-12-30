package com.technado.typicode.retrofit

import com.technado.typicode.helper.Constants.Companion.COMMENTS
import com.technado.typicode.helper.Constants.Companion.PHOTOS
import com.technado.typicode.helper.Constants.Companion.POSTS
import com.technado.typicode.helper.Constants.Companion.TODO
import com.technado.typicode.helper.Constants.Companion.USERS
import com.technado.typicode.models.*
import retrofit2.Call
import retrofit2.http.*

interface RetroServiceInterface {

    @GET(POSTS)
    fun getPostsList(
    ): Call<List<PostModel>>

    @GET(TODO)
    fun getTodoList(): Call<List<ToDoModel>>

    @GET(COMMENTS)
    fun getCommentsList(
        @Query(value = "postId", encoded = true) postId: String,
    ): Call<List<CommentModel>>

    @GET(USERS)
    fun getUsersList(): Call<List<UserModel>>

    @GET(PHOTOS)
    fun getPhotos(): Call<List<PhotosModel>>

}