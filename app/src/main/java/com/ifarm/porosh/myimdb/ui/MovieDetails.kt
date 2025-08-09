package com.ifarm.porosh.myimdb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.ifarm.porosh.myimdb.MainActivity
import com.ifarm.porosh.myimdb.R
import com.ifarm.porosh.myimdb.databinding.FragmentMovieDetailsBinding
import com.ifarm.porosh.myimdb.utilities.OtherUtil
import com.ifarm.porosh.myimdb.viewModels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetails : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    private val movieViewModel: MovieViewModel by viewModels()

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
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = mActivity.operationsViewModel.movieItem.value

        mActivity.supportActionBar?.title = movies?.title?:"Movie Details"

        Glide.with(binding.imgMovie).load(movies?.posterUrl).into(binding.imgMovie)

        binding.txtTitle.text = OtherUtil.Util.makeStyle(
            mActivity.resources.getString(R.string.title),
            movies?.title ?: ""
        )

        binding.txtActors.text = OtherUtil.Util.makeStyle(
            mActivity.resources.getString(R.string.actors),
            movies?.actors ?: ""
        )

        binding.txtDirector.text = OtherUtil.Util.makeStyle(
            mActivity.resources.getString(R.string.directors),
            movies?.director ?: ""
        )

        binding.txtYear.text = OtherUtil.Util.makeStyle(
            mActivity.resources.getString(R.string.year),
            movies?.year ?: ""
        )

        binding.txtDesc.text = OtherUtil.Util.makeStyle(
            mActivity.resources.getString(R.string.summary),
            movies?.movieDetails?: ""
        )

        movies?.let {
            movieViewModel.getMovieWithGenres(it.movieId).observe(viewLifecycleOwner) { genre ->
                val sb = StringBuilder()
                genre.genres.forEach { genre ->
                    sb.append(genre.name)
                    sb.append(",")
                }
                binding.txtGenre.text = OtherUtil.Util.makeStyle(
                    mActivity.resources.getString(R.string.genre),
                    sb.toString())
            }
        }

    }

}