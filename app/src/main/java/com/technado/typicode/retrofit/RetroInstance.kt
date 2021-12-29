package com.technado.typicode.retrofit

import com.technado.typicode.helper.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetroInstance {
    companion object {
        fun getRetroInstance(): Retrofit {
            val loggging = HttpLoggingInterceptor()
            loggging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
            client.addInterceptor(loggging)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}