package com.example.activitystarwapp.data.api

import com.example.activitystarwapp.data.model.CharacterModel
import com.example.activitystarwapp.data.model.PlanetsModel
import com.example.activitystarwapp.data.model.StarshipModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("people/")
    fun getPeoples() : Call<CharacterModel.Response>

    @GET("people/{id}")
    fun getPeopleId(@Path("id") id : Int) : Call<CharacterModel.Result>

    @GET("planets/")
    fun getPlanets() : Call<PlanetsModel.Response>

    @GET("starships/")
    fun getStarships() : Call<StarshipModel.Response>
}