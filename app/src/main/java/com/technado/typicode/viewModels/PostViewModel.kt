package com.technado.typicode.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.technado.typicode.models.PostModel
import com.technado.typicode.models.UserModel
import com.technado.typicode.retrofit.RetroInstance
import com.technado.typicode.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response

class PostViewModel(application: Application) : AndroidViewModel(application) {
    var postsList: MutableLiveData<List<PostModel>?>

    init {
        postsList = MutableLiveData()
    }

    fun getAllPosts(): MutableLiveData<List<PostModel>?> {
        val call =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
                .getPostsList()
        call.enqueue(object : retrofit2.Callback<List<PostModel>> {
            override fun onResponse(
                call: Call<List<PostModel>>?,
                response: Response<List<PostModel>>?
            ) {
                if (response?.code() == 200) {
                    postsList.postValue(response.body())
                } else {
                    postsList.postValue(null)
                    Toast.makeText(
                        getApplication(),
                        "Oops...!! Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<PostModel>>?, t: Throwable?) {
                postsList.postValue(null)
                Toast.makeText(
                    getApplication(),
                    "Oops...!! Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return postsList
    }
}