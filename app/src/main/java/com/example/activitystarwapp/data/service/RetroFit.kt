package com.example.activitystarwapp.data.service

import com.example.activitystarwapp.data.api.Endpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFit {

    companion object{
        fun getRetrofitInstance(path: String) : Retrofit {
            return  Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun setRetrofit() : Endpoint {
            val retroFit = getRetrofitInstance("https://swapi.dev/api/")
            return retroFit.create(Endpoint ::class.java)
        }

    }
}