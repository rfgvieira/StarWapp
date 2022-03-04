package com.example.model

import com.google.gson.annotations.SerializedName
class CharacterModel {

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

        @SerializedName("height")
        val height: String,

        @SerializedName("mass")
        val mass: String,

        @SerializedName("hair_color")
        val hair_Color: String,

        @SerializedName("skin_color")
        val skin_Color: String,

        @SerializedName("eye_color")
        val eye_Color: String,

        @SerializedName("birth_year")
        val birth_Year: String,

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

