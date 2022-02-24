package com.example.activitystarwapp.data.api

import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.model.StarshipModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("people/")
    suspend fun getPeoples() : Response<CharacterModel.Response>

    @GET("people/{id}")
    suspend fun getPeopleId(@Path("id") id : Int) : Response<CharacterModel.Result>

    @GET("planets/")
    suspend fun getPlanets() : Response<PlanetsModel.Response>

    @GET("planets/{id}")
    suspend fun getPlanetsId(@Path("id") id : Int) : Response<PlanetsModel.Result>

    @GET("starships/")
    suspend fun getStarships() : Response<StarshipModel.Response>

    @GET("starships/{id}")
    suspend fun getStarshipsId(@Path("id") id : Int) : Response<StarshipModel.Result>
}