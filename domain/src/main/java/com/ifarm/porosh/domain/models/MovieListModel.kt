package com.ifarm.porosh.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListModel(
    @param:Json(name = "genres")
    val genres: List<String>,

    @param:Json(name = "movies")
    val movies: List<Movie>
)