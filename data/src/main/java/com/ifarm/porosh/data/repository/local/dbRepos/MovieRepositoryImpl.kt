package com.ifarm.porosh.data.repository.local.dbRepos

import android.util.Log
import androidx.lifecycle.LiveData
import com.ifarm.porosh.data.local.db.daos.GenreDao
import com.ifarm.porosh.data.local.db.daos.MovieGenreRefDao
import com.ifarm.porosh.data.local.db.daos.MoviesDao
import com.ifarm.porosh.data.local.db.daos.WishListDao
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.local.db.entities.MovieGenreRef
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.data.local.db.entities.WishList
import com.ifarm.porosh.data.local.db.relations.GenreWithMovies
import com.ifarm.porosh.data.local.db.relations.MovieWithGenres
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    val movieDao: MoviesDao,
    val genreDao: GenreDao,
    val wishListDao: WishListDao,
    val movieGenreRefDao: MovieGenreRefDao
) : MovieRepository {

    override suspend fun isDataStored() {

    }

    /*
    * Movies operations
    * */
    override suspend fun insertMovies(movies: List<Movies>) {
        movieDao.insertMovies(movies)
    }

    override suspend fun getMoviesPaginated(
        limit: Int,
        offset: Int
    ): List<Movies> {
        return movieDao.getMoviesPaginated(limit,offset)
    }

    override fun getMovieWithGenres(movieId: Int): LiveData<MovieWithGenres> {
       return movieDao.getMovieWithGenres(movieId)
    }

    override suspend fun clearMovies() {
        TODO("Not yet implemented")
    }


    /*
    * Genre methods
    * */
    override suspend fun insertGenre(genre: Genre): Long {
        return genreDao.insertGenre(genre)
    }

    override suspend fun insertGenres(genres: List<Genre>): List<Long> {
        return genreDao.insertGenres(genres)
    }

    override fun getAllGenres(): LiveData<List<Genre>> {
        TODO("Not yet implemented")
    }

    override fun getGenreWithMovies(id: Long): LiveData<GenreWithMovies> {
        TODO("Not yet implemented")
    }

    /*
    * wishlist operations
    * */
    override suspend fun addToWishlist(item: WishList) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromWishlist(item: WishList) {
        TODO("Not yet implemented")
    }

    override suspend fun removeByMovieId(movieId: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllWishlistItems(): LiveData<List<WishList>> {
        TODO("Not yet implemented")
    }

    override fun getWishlistMovies(): LiveData<List<Movies>> {
        TODO("Not yet implemented")
    }

    override suspend fun getWishlistCount(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun isInWishlist(movieId: Int): Boolean {
        TODO("Not yet implemented")
    }

    /*
    * Movie Genre Ref operations
    * */
    override suspend fun insertRef(ref: MovieGenreRef) {
        TODO("Not yet implemented")
    }

    override suspend fun insertMovieGenreRefs(refs: List<MovieGenreRef>) {
        //Log.i("data_module","Movie Genre Ref repo - : ${refs[0].movieId} ${refs[0].genreId}")
        movieGenreRefDao.insertMovieGenreRefs(refs)
    }

}