package com.technado.typicode.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.technado.typicode.helper.Dialog_CustomProgress
import com.technado.typicode.models.UserModel
import com.technado.typicode.retrofit.RetroInstance
import com.technado.typicode.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application) {
    var usersList: MutableLiveData<List<UserModel>?>

    init {
        usersList = MutableLiveData()
    }

    fun getAllUsers(): MutableLiveData<List<UserModel>?> {
        val call =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
                .getUsersList()
        call.enqueue(object : retrofit2.Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>?,
                response: Response<List<UserModel>>?
            ) {
                if (response?.code() == 200) {
                    usersList.postValue(response.body())
                } else {
                    usersList.postValue(null)
                    Toast.makeText(
                        getApplication(),
                        "Oops...!! Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<UserModel>>?, t: Throwable?) {
                usersList.postValue(null)
                Toast.makeText(
                    getApplication(),
                    "Oops...!! Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return usersList
    }
}