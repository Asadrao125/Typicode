package com.technado.typicode.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.technado.typicode.fragments.PostsFragment
import com.technado.typicode.models.CommentModel
import com.technado.typicode.models.PostModel
import com.technado.typicode.models.UserModel
import com.technado.typicode.retrofit.RetroInstance
import com.technado.typicode.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response

class CommentViewModel(application: Application) : AndroidViewModel(application) {
    var commentsList: MutableLiveData<List<CommentModel>?>

    init {
        commentsList = MutableLiveData()
    }

    fun getAllComments(): MutableLiveData<List<CommentModel>?> {
        val call =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
                .getCommentsList(PostsFragment.postId)
        call.enqueue(object : retrofit2.Callback<List<CommentModel>> {
            override fun onResponse(
                call: Call<List<CommentModel>>?,
                response: Response<List<CommentModel>>?
            ) {
                if (response?.code() == 200) {
                    commentsList.postValue(response.body())
                } else {
                    commentsList.postValue(null)
                    Toast.makeText(
                        getApplication(),
                        "Oops...!! Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<CommentModel>>?, t: Throwable?) {
                commentsList.postValue(null)
                Toast.makeText(
                    getApplication(),
                    "Oops...!! Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return commentsList
    }
}