package com.ifarm.porosh.myimdb

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.remote.apiResponse.ApiResponse
import com.ifarm.porosh.myimdb.databinding.ActivitySplashScreenBinding
import com.ifarm.porosh.myimdb.viewModels.DataStoreViewModel
import com.ifarm.porosh.myimdb.viewModels.MovieViewModel
import com.ifarm.porosh.myimdb.viewModels.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val networkViewModel: NetworkViewModel by viewModels()
    private val movieViewModel: MovieViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        fetchMovieData()

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

    }

    private fun waitSplash(isDataStored: Boolean){
        val splashThread = object : Thread() {
            override fun run() {
                try {
                    sleep(8000) // 3 seconds delay
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                    finish()
                }
            }
        }
        splashThread.start()
    }

    private fun fetchMovieData(){
        networkViewModel.fetchMovieList().observe(this) { response ->
            when(response) {
                is ApiResponse.Loading -> {
                    Log.i("network_module","Loading.....")
                    //progressDialog.show()
                }

                is ApiResponse.Success -> {
                    //progressDialog.dismiss()
                    val movieData = response.data
                    val movieGenres = movieData.genres
                    val movieList = movieData.movies
                    if (movieData !=null){
                        /////

                        // 1. Insert genres and keep a map of name -> id

                        val genresMap = mutableMapOf<String, Long>()
                        movieList.flatMap { it.genres }.distinct().forEach { genreName ->
                             movieViewModel.insertGenre(Genre(name = genreName)).observe(this){ id ->
                                 genresMap[genreName] = id
                                 Log.i("data_module","Genre List - response: $id")
                             }
                        }
                        Log.i("data_module","Genre map - response: ${genresMap.forEach { data ->
                            data 
                        }}")

                        /*// 2. Insert movies
                        movieDao.insertMovies(movies.map { it.toEntity() })

                        // 3. Insert cross refs
                        movies.forEach { movie ->
                            val movieId = movie.id
                            val crossRefs = movie.genres.map { genreName ->
                                MovieGenreCrossRef(movieId, genresMap[genreName]!!)
                            }
                            crossRefDao.insertCrossRefs(crossRefs)
                        }*/

                        /////
                        Log.i("network_module","Movie List - response: ${movieList[0].title}")
                    }else{
                        Log.i("network_module","Movie List - response: Unsuccessful")
                    }
                }

                is ApiResponse.Error -> {
                    //progressDialog.dismiss()
                    //DialogUtil(mActivity).showNotifyDialog(response.message)
                    Log.i("network_module","Movie List - response: Unsuccessful ${response.message}")
                }

            }

            waitSplash(true)

        }

    }

}