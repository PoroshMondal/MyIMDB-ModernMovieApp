package com.ifarm.porosh.data.local.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.local.db.entities.MovieGenreRef
import com.ifarm.porosh.data.local.db.entities.Movies

/*
* relation classes for Data Access
* */
data class GenreWithMovies(
    @Embedded val genre: Genre,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "id",
        associateBy = Junction(
            value = MovieGenreRef::class,
            parentColumn = "genreId",
            entityColumn = "movieId"
        )
    )
    val movies: List<Movies>
)
