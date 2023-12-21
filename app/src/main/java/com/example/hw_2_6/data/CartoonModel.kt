package com.example.hw_2_6.data

import java.io.Serializable

data class CartoonModel(
    val results: List<Result>
):Serializable{

    data class Result(
        val id: Int,
        val image: String,
        val location: Location,
        val name: String,
        val origin: Origin,
        val species: String,
        val status: String,
    ):Serializable {
        data class Location(
            val name: String,
            val url: String
        ):Serializable

        data class Origin(
            val name: String,
            val url: String
        ):Serializable
    }
}