package com.ifarm.porosh.data.remote

import com.ifarm.porosh.data.remote.apiServices.MovieServicesApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val weather_api_key = "e9260defcaa80ebeb476f4b91d6233d1"
//const val base_url = "https://api.openweathermap.org/data/2.5/"
const val base_url = "https://raw.githubusercontent.com/"
const val endUrl = "erik-sytnyk/movies-list/master/db.json"

private val moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object RetrofitInstance {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor { getTokenFromPreferences() })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(base_url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    val movieServiceApi: MovieServicesApi by lazy {
        retrofit.create(MovieServicesApi::class.java)
    }

    private fun getTokenFromPreferences(): String {
        return "xyz123"  // Fetch from Secure Storage
    }

}
