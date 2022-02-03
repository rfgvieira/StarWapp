package com.example.activitystarwapp

import com.google.gson.annotations.SerializedName
class CharacterModel {

    data class Info(
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

        @SerializedName("height")
        val height: String,

        @SerializedName("mass")
        val mass: String,

        @SerializedName("hairColor")
        val hairColor: String,

        @SerializedName("skinColor")
        val skinColor: String,

        @SerializedName("eyeColor")
        val eyeColor: String,

        @SerializedName("birthYear")
        val birthYear: String,

        @SerializedName("gender")
        val gender: String,

        @SerializedName("homeworld")
        val homeworld: String,

        @SerializedName("films")
        val films: List<String>,

        @SerializedName("species")
        val species: List<String>,

        @SerializedName("vehicles")
        val vehicles: List<String>,

        @SerializedName("starships")
        val starships: List<String>,

        @SerializedName("created")
        val created: String,

        @SerializedName("edited")
        val edited: String,

        @SerializedName("url")
        val url: String

    )

    enum class Gender(val value: String) {
        Female("female"),
        Male("male"),
        NA("n/a");

        companion object {
            public fun fromValue(value: String): Gender = when (value) {
                "female" -> Female
                "male"   -> Male
                "n/a"    -> NA
                else     -> throw IllegalArgumentException()
            }
        }
    }
}

