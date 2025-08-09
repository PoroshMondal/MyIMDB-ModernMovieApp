package com.ifarm.porosh.myimdb.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.myimdb.databinding.SingleRowMoviesBinding

class MoviesAdapter (private val callBack: (Movies, Int) -> Unit): ListAdapter<Movies, MoviesAdapter.MovieViewHolder>(RVDiffCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = SingleRowMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding,callBack)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val data = getItem(position)
        holder.bind(data, position)
    }

    class MovieViewHolder(
        private val binding: SingleRowMoviesBinding,
        private val callback: (movieModel: Movies, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(movieItem: Movies, position: Int){
            binding.movies = movieItem
            binding.layoutSingleMovieItem.setOnClickListener {
                callback(movieItem,position)
            }

            Log.i("movies_rv","RV - update movie ID: ${movieItem.movieId}")

            /*binding.layoutSingleRowItem.setOnClickListener {
                LogUtil.Log.debugVisit("RV - update adapter position check: $adapterPosition pos: $position")
                callback(data, CCTConstants.UPDATE, adapterPosition)
            }

            binding.imgDelete.setOnClickListener {
                LogUtil.Log.debugVisit("RV - del adapter position check: $adapterPosition pos: $position")
                callback(data, CCTConstants.DELETE, adapterPosition)
            }*/

        }
    }

    class RVDiffCallBack: DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
            //return false
        }

    }

}