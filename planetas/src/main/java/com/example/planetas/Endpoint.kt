package com.example.planetas

import com.example.model.PlanetsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("planets/")
    suspend fun getPlanets() : Response<PlanetsModel.Response>

    @GET("planets/{id}")
    suspend fun getPlanetsId(@Path("id") id : Int) : Response<PlanetsModel.Result>
}