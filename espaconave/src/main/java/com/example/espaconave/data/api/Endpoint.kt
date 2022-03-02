package com.example.espaconave.data.api

import com.example.espaconave.data.model.StarshipModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("starships/")
    suspend fun getStarships() : Response<StarshipModel.Response>

    @GET("starships/{id}")
    suspend fun getStarshipsId(@Path("id") id : Int) : Response<StarshipModel.Result>
}