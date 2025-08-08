package com.ifarm.porosh.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey(autoGenerate = true)
    //val genreId: Int,
    val genreId: Long = 0,

    val name: String
)
