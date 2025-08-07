package com.ifarm.porosh.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @param:Json(name = "actors")
    val actors: String,

    @param:Json(name = "director")
    val director: String,

    @param:Json(name = "genres")
    val genres: List<String>,

    @param:Json(name = "id")
    val id: Int,

    @param:Json(name = "plot")
    val plot: String,

    @param:Json(name = "posterUrl")
    val posterUrl: String,

    @param:Json(name = "runtime")
    val runtime: String,

    @param:Json(name = "title")
    val title: String,

    @param:Json(name = "year")
    val year: String
)