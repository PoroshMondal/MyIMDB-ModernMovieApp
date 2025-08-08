package com.ifarm.porosh.myimdb.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.local.db.entities.MovieGenreRef
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.data.local.db.entities.WishList
import com.ifarm.porosh.data.local.db.relations.GenreWithMovies
import com.ifarm.porosh.data.local.db.relations.MovieWithGenres
import com.ifarm.porosh.data.repository.local.dbRepos.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {
    /*
    * Movies operations
    * */
    fun insertMovies(movies: List<Movies>) {
        viewModelScope.launch {
            movieRepository.insertMovies(movies)
        }
    }

    fun getMoviesPaginated(
        limit: Int,
        offset: Int
    ): LiveData<List<Movies>> {
        TODO("Not yet implemented")
    }

    fun getMovieWithGenres(movieId: Int): LiveData<MovieWithGenres> {
        TODO("Not yet implemented")
    }

    fun clearMovies() {
        TODO("Not yet implemented")
    }


    /*
    * Genre methods
    * */
    fun insertGenre(genre: Genre): Long {
        TODO("Not yet implemented")
    }

    fun insertGenres(genres: List<Genre>): List<Long> {
        TODO("Not yet implemented")
    }

    fun getAllGenres(): LiveData<List<Genre>> {
        TODO("Not yet implemented")
    }

    fun getGenreWithMovies(id: Long): LiveData<GenreWithMovies> {
        TODO("Not yet implemented")
    }

    /*
    * wishlist operations
    * */
    fun addToWishlist(item: WishList) {
        TODO("Not yet implemented")
    }

    fun removeFromWishlist(item: WishList) {
        TODO("Not yet implemented")
    }

    fun removeByMovieId(movieId: Int) {
        TODO("Not yet implemented")
    }

    fun getAllWishlistItems(): LiveData<List<WishList>> {
        TODO("Not yet implemented")
    }

    fun getWishlistMovies(): LiveData<List<Movies>> {
        TODO("Not yet implemented")
    }

    fun getWishlistCount(): Int {
        TODO("Not yet implemented")
    }

    fun isInWishlist(movieId: Int): Boolean {
        TODO("Not yet implemented")
    }

    /*
    * Movie Genre Ref operations
    * */
    fun insertRef(ref: MovieGenreRef) {
        TODO("Not yet implemented")
    }

    fun insertMovieGenreRefs(refs: List<MovieGenreRef>) {
        TODO("Not yet implemented")
    }

}