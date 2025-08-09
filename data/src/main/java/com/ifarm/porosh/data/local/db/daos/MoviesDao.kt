package com.ifarm.porosh.data.local.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.data.local.db.relations.MovieWithGenres

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movies)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movies>)

    @Transaction
    @Query("SELECT * FROM movies ORDER BY year DESC LIMIT :limit OFFSET :offset")
    suspend fun getMoviesPaginated(limit: Int, offset: Int): List<Movies>

    @Transaction
    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovieWithGenres(movieId: Int): LiveData<MovieWithGenres>

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}