package com.ifarm.porosh.myimdb.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.local.db.entities.MovieGenreRef
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.data.local.db.entities.WishList
import com.ifarm.porosh.data.local.db.relations.GenreWithMovies
import com.ifarm.porosh.data.local.db.relations.MovieWithGenres
import com.ifarm.porosh.data.repository.local.dbRepos.MovieRepository
import com.ifarm.porosh.domain.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    // inserted genre and movieGenreRef ids
    // as it is depended on genre ids
    // this method used inserted both
    suspend fun insertGenreAndMovieGenRef(movies: List<Movie>){
        val genresMap = mutableMapOf<String, Long>()

        movies.flatMap { it.genres }
            .distinct()
            .forEach { genreName ->
                val id = movieRepository.insertGenre(Genre(name = genreName))
                genresMap[genreName] = id
                Log.i("data_module","In viewmodel Genre IDs - response: $id")
            }

        Log.i("data_module","In viewmodel Genre - response: ${genresMap.size}")

        movies.forEach { movie ->
            val movieId = movie.id
            val crossRefs = movie.genres.mapNotNull { genreName ->
                Log.i("data_module","In viewmodel Genre 2 - response: ${genresMap[genreName]}")
                genresMap[genreName]?.let { MovieGenreRef(movieId, it) }
            }
            insertMovieGenreRefs(crossRefs)
        }
    }

    fun insertGenre(genre: Genre): LiveData<Long> {
        val result = MutableLiveData<Long>()
        viewModelScope.launch {
            val id = movieRepository.insertGenre(genre)
            result.postValue(id)
        }
        return result
    }

    fun insertGenres(genres: List<Genre>): LiveData<List<Long>> {
        val result = MutableLiveData<List<Long>>()
        viewModelScope.launch {
            val ids = movieRepository.insertGenres(genres)
            result.postValue(ids)
        }
        return result
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
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.insertMovieGenreRefs(refs)
        }
    }

}