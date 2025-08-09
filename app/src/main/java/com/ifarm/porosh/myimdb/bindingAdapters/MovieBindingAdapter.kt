package com.ifarm.porosh.myimdb.bindingAdapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/*
* will implement later
* a dto need to used
* based on db schema
* */
/*@BindingAdapter("app:setMovieGenre")
fun setMovieGenre(tv: TextView, genreList: MutableList<String>) {
    val gName = genreList.joinToString(" ")
    tv.text = gName
}*/

@BindingAdapter("app:setMovieImage")
fun setMovieImage(iv: ImageView, imageUrl: String) {
    if (imageUrl.isNotEmpty()){
        Glide.with(iv).load(imageUrl).into(iv)
    }
}
