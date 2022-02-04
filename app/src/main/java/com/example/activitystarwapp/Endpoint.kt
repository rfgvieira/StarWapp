package com.example.activitystarwapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    @GET("people/")
    fun getPeoples() : Call<CharacterModel.Info>

    @GET("people/{id}")
    fun getPeopleId(@Path("id") id : Int) : Call<CharacterModel.Result>

    @GET("planets/")
    fun getPlanets() : Call<PlanetsModel.Info>

    @GET("starships/")
    fun getStarships() : Call<StarshipModel.Info>
}