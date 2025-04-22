package com.example.rasoighar

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun Client(): Retrofit{
        return Retrofit.Builder().baseUrl("http://172.20.10.9:8000/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}