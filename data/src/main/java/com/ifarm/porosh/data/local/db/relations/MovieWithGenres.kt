package com.ifarm.porosh.data.local.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.local.db.entities.MovieGenreRef
import com.ifarm.porosh.data.local.db.entities.Movies

data class MovieWithGenres(
    @Embedded val movie: Movies,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreRef::class)
    )
    val genres: List<Genre>
)
