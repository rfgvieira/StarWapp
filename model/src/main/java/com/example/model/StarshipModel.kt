package com.example.model

import com.google.gson.annotations.SerializedName

class StarshipModel {
    data class Response(
        @SerializedName("count")
        val count: Long,

        @SerializedName("next")
        val next: String,

        @SerializedName("previous")
        val previous: Any? = null,

        @SerializedName("results")
        val results: List<Result>
    )

    data class Result (
        @SerializedName("name")
        val name: String,

        @SerializedName("model")
        val model: String,

        @SerializedName("manufacturer")
        val manufacturer: String,

        @SerializedName("costInCredits")
        val costInCredits: String,

        @SerializedName("length")
        val length: String,

        @SerializedName("maxAtmospheringSpeed")
        val maxAtmospheringSpeed: String,

        @SerializedName("crew")
        val crew: String,

        @SerializedName("passengers")
        val passengers: String,

        @SerializedName("cargoCapacity")
        val cargoCapacity: String,

        @SerializedName("consumables")
        val consumables: String,

        @SerializedName("hyperdriveRating")
        val hyperdriveRating: String,

        @SerializedName("mglt")
        val mglt: String,

        @SerializedName("starshipClass")
        val starshipClass: String,

        @SerializedName("pilots")
        val pilots: List<String>,

        @SerializedName("films")
        val films: List<String>,

        @SerializedName("created")
        val created: String,

        @SerializedName("edited")
        val edited: String,

        @SerializedName("url")
        val url: String
    )
}