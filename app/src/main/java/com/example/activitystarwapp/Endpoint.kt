package com.example.activitystarwapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("people/")
    fun getPeople() : Call<CharacterModel.Result>
}