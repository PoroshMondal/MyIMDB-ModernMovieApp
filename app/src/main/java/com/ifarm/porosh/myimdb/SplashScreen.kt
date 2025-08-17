package com.ifarm.porosh.myimdb

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.data.remote.apiResponse.ApiResponse
import com.ifarm.porosh.domain.models.Movie
import com.ifarm.porosh.myimdb.components.NetworkStatusReceiver
import com.ifarm.porosh.myimdb.databinding.ActivitySplashScreenBinding
import com.ifarm.porosh.myimdb.utilities.OtherUtil
import com.ifarm.porosh.myimdb.viewModels.DataStoreViewModel
import com.ifarm.porosh.myimdb.viewModels.MovieViewModel
import com.ifarm.porosh.myimdb.viewModels.NetworkStatusViewModel
import com.ifarm.porosh.myimdb.viewModels.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val networkStatusMonitorViewModel: NetworkStatusViewModel by viewModels()
    private val networkViewModel: NetworkViewModel by viewModels()
    private val movieViewModel: MovieViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //register network change broadcast receiver
        networkStatusMonitorViewModel.connectionStatus.observe(this){ isConnected ->
            if (isConnected){
                Log.i("network_status", "Is connected: $isConnected")
            }else {
                Log.i("network_status", "Is not connected: $isConnected")
            }
        }
        //////////////////

        dataStoreViewModel.getIsDataStored().observeOnce(this){ isStored ->
            if (!isStored){
                if (OtherUtil.NetworkUtils.isConnected(this)){
                    fetchMovieData()
                }else{
                    // show a dialog

                }
                Log.i("splashscreen","Data is not stored fetching data")
            }else{
                Log.i("splashscreen","data stored moving to next screen")
                //waitSplash()
            }
        }

        // waiting time and functions will modify later
        //waitSplash()

    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart called", Toast.LENGTH_SHORT).show()
        networkStatusMonitorViewModel.startNetworkMonitoring()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this,"onResume called", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this,"onStop called", Toast.LENGTH_SHORT).show()
        networkStatusMonitorViewModel.stopNetworkMonitoring()
    }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(value: T) {
                observer(value)
                removeObserver(this)
            }
        })
    }


    private fun waitSplash(){
        val splashThread = object : Thread() {
            override fun run() {
                try {
                    sleep(8000)
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
                    val movies = movieData.movies

                    insertMovies(movies)

                    lifecycleScope.launch(Dispatchers.IO){
                        movieViewModel.insertGenreAndMovieGenRef(movies)
                        dataStoreViewModel.setDataStoredInfo(true)
                    }

                    //val movieGenres = movieData.genres
                    /*movieViewModel.insertGenres(genreNameList).observe(this){ idList ->
                    idList.forEach { id ->
                        //genresMap[genreName] = id
                        Log.i("data_module","Genre list id - response: $id")
                    }
                }*/

                }

                is ApiResponse.Error -> {
                    //progressDialog.dismiss()
                    dataStoreViewModel.setDataStoredInfo(false)
                    Log.i("network_module","Movie List - response: Unsuccessful ${response.message}")
                }

            }

        }

    }

    private fun insertMovies(movies: List<Movie>){
        val movieList = mutableListOf<Movies>()
        movies.forEach { it ->
            movieList.add(
                Movies(
                    it.id,
                    it.title,
                    it.year,
                    it.runtime,
                    it.director,
                    it.actors,
                    it.plot,
                    it.posterUrl)
            )
        }

        movieViewModel.insertMovies(movieList)
        dataStoreViewModel.setDataStoredInfo(true)
        Log.i("data_module","Movie List - response: ${movieList.size}")
    }

}