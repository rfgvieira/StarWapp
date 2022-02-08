package com.example.activitystarwapp.data.model

import com.google.gson.annotations.SerializedName

class PlanetsModel {
    data class Response(
        @SerializedName("count")
        val count: Long? = 0L,

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

        @SerializedName("rotationPeriod")
        val rotationPeriod: String,

        @SerializedName("orbitalPeriod")
        val orbitalPeriod: String,

        @SerializedName("diameter")
        val diameter: String,

        @SerializedName("climate")
        val climate: String,

        @SerializedName("gravity")
        val gravity: String,

        @SerializedName("terrain")
        val terrain: String,

        @SerializedName("surfaceWater")
        val surfaceWater: String,

        @SerializedName("population")
        val population: String,

        @SerializedName("residents")
        val residents: List<String>,

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