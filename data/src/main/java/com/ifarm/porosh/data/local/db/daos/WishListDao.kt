package com.ifarm.porosh.data.local.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.data.local.db.entities.WishList

@Dao
interface WishListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWishlist(item: WishList)

    @Delete
    suspend fun removeFromWishlist(item: WishList)

    @Query("DELETE FROM wishlist WHERE movieId = :movieId")
    suspend fun removeByMovieId(movieId: Int)

    @Query("SELECT * FROM wishlist")
    fun getAllWishlistItems(): LiveData<List<WishList>>

    @Transaction
    @Query("""
        SELECT * FROM movies
        INNER JOIN wishlist ON movies.movieId = wishlist.movieId
    """)
    fun getWishlistMovies(): LiveData<List<Movies>>

    @Query("SELECT COUNT(*) FROM wishlist")
    suspend fun getWishlistCount(): Int

    @Query("SELECT EXISTS(SELECT 1 FROM wishlist WHERE movieId = :movieId)")
    suspend fun isInWishlist(movieId: Int): Boolean
}