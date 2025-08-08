package com.ifarm.porosh.data.local.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.local.db.relations.GenreWithMovies

@Dao
interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: Genre): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<Genre>): List<Long>

    @Transaction
    @Query("SELECT * FROM genres")
    fun getAllGenres(): LiveData<List<Genre>>

    @Transaction
    @Query("SELECT * FROM genres WHERE genreId = :id")
    fun getGenreWithMovies(id: Long): LiveData<GenreWithMovies>
}