package com.ifarm.porosh.myimdb.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.myimdb.MainActivity
import com.ifarm.porosh.myimdb.R
import com.ifarm.porosh.myimdb.adapters.MoviesAdapter
import com.ifarm.porosh.myimdb.databinding.FragmentMovieListBinding
import com.ifarm.porosh.myimdb.viewModels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieList : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter
    private lateinit var mActivity: MainActivity
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)

        adapter = MoviesAdapter(::callBack)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.adapter = adapter

        movieViewModel.loadNextPage()

        movieViewModel.movies.observe(viewLifecycleOwner){ movies ->
            Log.i("moviescreen","Movie item: ${movies.size}")
            adapter.submitList(movies.toMutableList())
        }

        binding.rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val totalItems = layoutManager.itemCount

                if (lastVisibleItem >= totalItems - 2) {
                    movieViewModel.loadNextPage()
                }

            }
        })

    }

    private fun callBack(movie: Movies, position: Int){
        mActivity.operationsViewModel.setMoviesData(movie)
        Toast.makeText(requireContext(),"Movie: ${movie.title} postiion: $position", Toast.LENGTH_SHORT).show()
        navController.navigate(R.id.movieDetails, null, mActivity.clearBackStack())
    }

}