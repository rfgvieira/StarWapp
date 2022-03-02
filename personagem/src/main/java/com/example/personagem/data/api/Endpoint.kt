package com.example.personagem.data.api

import com.example.personagem.data.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("people/")
    suspend fun getPeoples() : Response<CharacterModel.Response>

    @GET("people/{id}")
    suspend fun getPeopleId(@Path("id") id : Int) : Response<CharacterModel.Result>
}