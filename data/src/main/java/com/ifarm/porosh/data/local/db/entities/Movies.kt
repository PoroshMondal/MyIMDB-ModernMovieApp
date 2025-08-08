package com.ifarm.porosh.data.local.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movies(
    @PrimaryKey val movieId: Int,

    val title: String,
    val year: String,
    val runtime: String,
    val director: String,
    val actors: String,

    @ColumnInfo(name = "plot")
    val movieDetails: String,

    val posterUrl: String
)
