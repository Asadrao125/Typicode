package com.technado.typicode.viewModels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.technado.typicode.models.CommentModel
import com.technado.typicode.models.PostModel
import com.technado.typicode.models.ToDoModel
import com.technado.typicode.models.UserModel
import com.technado.typicode.retrofit.RetroInstance
import com.technado.typicode.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Response

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    var todoList: MutableLiveData<List<ToDoModel>?>

    init {
        todoList = MutableLiveData()
    }

    fun getToDoList(): MutableLiveData<List<ToDoModel>?> {
        val call =
            RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
                .getTodoList()
        call.enqueue(object : retrofit2.Callback<List<ToDoModel>> {
            override fun onResponse(
                call: Call<List<ToDoModel>>?,
                response: Response<List<ToDoModel>>?
            ) {
                if (response?.code() == 200) {
                    todoList.postValue(response.body())
                } else {
                    todoList.postValue(null)
                    Toast.makeText(
                        getApplication(),
                        "Oops...!! Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<ToDoModel>>?, t: Throwable?) {
                todoList.postValue(null)
                Toast.makeText(
                    getApplication(),
                    "Oops...!! Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        return todoList
    }
}