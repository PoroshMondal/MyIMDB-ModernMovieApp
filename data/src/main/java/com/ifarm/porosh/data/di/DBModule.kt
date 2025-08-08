package com.ifarm.porosh.data.di

import android.content.Context
import androidx.room.Room
import com.ifarm.porosh.data.local.IMDBDatabase
import com.ifarm.porosh.data.local.db.daos.GenreDao
import com.ifarm.porosh.data.local.db.daos.MovieGenreRefDao
import com.ifarm.porosh.data.local.db.daos.MoviesDao
import com.ifarm.porosh.data.local.db.daos.WishListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    /*@Provides
    fun provideDatabase(@ApplicationContext context: Context): IMDBDatabase {
        return Room.databaseBuilder(
            context.applicationContext, IMDBDatabase::class.java, DB_NAME)
            //.fallbackToDestructiveMigration()
            .build()
    }*/

    @Provides
    fun provideMoviesDao(@ApplicationContext context: Context): MoviesDao {
        return IMDBDatabase.getDbInstance(context).moviesDao()
    }

    @Provides
    fun provideGenreDao(@ApplicationContext context: Context): GenreDao {
        return IMDBDatabase.getDbInstance(context).genreDao()
    }

    @Provides
    fun provideMovieGenreRefDao(@ApplicationContext context: Context): MovieGenreRefDao {
        return IMDBDatabase.getDbInstance(context).movieGenreRefDao()
    }

    @Provides
    fun provideWishListDao(@ApplicationContext context: Context): WishListDao {
        return IMDBDatabase.getDbInstance(context).wishListDao()
    }

    /*@Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return DataStoreRepositoryImpl(context)
    }*/

}