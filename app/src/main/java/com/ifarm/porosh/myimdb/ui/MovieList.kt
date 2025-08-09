package com.ifarm.porosh.myimdb.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.domain.models.Movie
import com.ifarm.porosh.myimdb.adapters.EndlessScrollListener
import com.ifarm.porosh.myimdb.adapters.MoviesAdapter
import com.ifarm.porosh.myimdb.databinding.FragmentMovieListBinding
import com.ifarm.porosh.myimdb.viewModels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MovieList : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        adapter = MoviesAdapter(::callBack)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.adapter = adapter

        // First load
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

                if (lastVisibleItem >= totalItems - 2) { // Load when near end
                    movieViewModel.loadNextPage()
                }

            }
        })

        /*binding.rvMovies.addOnScrollListener(object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int) {
                loadMoreData(page)
            }

        })*/

    }

    private fun callBack(movie: Movies, position: Int){
        Toast.makeText(requireContext(),"Movie: ${movie.title} postiion: $position", Toast.LENGTH_SHORT).show()
    }

}