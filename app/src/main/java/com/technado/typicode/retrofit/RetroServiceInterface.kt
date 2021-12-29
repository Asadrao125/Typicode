package com.technado.typicode.retrofit

import com.technado.typicode.helper.Constants.Companion.COMMENTS
import com.technado.typicode.helper.Constants.Companion.POSTS
import com.technado.typicode.helper.Constants.Companion.TODO
import com.technado.typicode.helper.Constants.Companion.USERS
import com.technado.typicode.models.CommentModel
import com.technado.typicode.models.PostModel
import com.technado.typicode.models.ToDoModel
import com.technado.typicode.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetroServiceInterface {

    @GET(POSTS)
    fun getPostsList(): Call<List<PostModel>>

    @GET(TODO)
    fun getTodoList(): Call<List<ToDoModel>>

    @GET(COMMENTS)
    fun getCommentsList(): Call<List<CommentModel>>

    @GET(USERS)
    fun getUsersList(): Call<List<UserModel>>

}