package com.ifarm.porosh.data.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.local.db.entities.MovieGenreRef
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.data.local.db.entities.WishList
import com.ifarm.porosh.data.local.db.relations.GenreWithMovies
import com.ifarm.porosh.data.local.db.relations.MovieWithGenres

interface MovieRepository {
    suspend fun isDataStored()

    /*
    * Movies methods
    * */
    suspend fun insertMovies(movies: List<Movies>)

    fun getMoviesPaginated(limit: Int, offset: Int): LiveData<List<Movies>>

    fun getMovieWithGenres(movieId: Int): LiveData<MovieWithGenres>

    suspend fun clearMovies()

    /*
    * Genre methods
    * */
    suspend fun insertGenre(genre: Genre): Long

    suspend fun insertGenres(genres: List<Genre>): List<Long>

    fun getAllGenres(): LiveData<List<Genre>>

    fun getGenreWithMovies(id: Long): LiveData<GenreWithMovies>


    /*
    * wishlist methods
    * */
    suspend fun addToWishlist(item: WishList)

    suspend fun removeFromWishlist(item: WishList)

    suspend fun removeByMovieId(movieId: Int)

    fun getAllWishlistItems(): LiveData<List<WishList>>

    fun getWishlistMovies(): LiveData<List<Movies>>

    @Query("SELECT COUNT(*) FROM wishlist")
    suspend fun getWishlistCount(): Int

    @Query("SELECT EXISTS(SELECT 1 FROM wishlist WHERE movieId = :movieId)")
    suspend fun isInWishlist(movieId: Int): Boolean

    /*
    * Movie Genre Ref methods
    * */
    suspend fun insertRef(ref: MovieGenreRef)

    suspend fun insertMovieGenreRefs(refs: List<MovieGenreRef>)

}
