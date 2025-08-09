package com.ifarm.porosh.data.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ifarm.porosh.data.local.db.entities.MovieGenreRef

@Dao
interface MovieGenreRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRef(ref: MovieGenreRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieGenreRefs(refs: List<MovieGenreRef>)
}