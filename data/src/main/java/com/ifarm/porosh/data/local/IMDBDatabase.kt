package com.ifarm.porosh.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ifarm.porosh.data.local.db.daos.GenreDao
import com.ifarm.porosh.data.local.db.daos.MovieGenreRefDao
import com.ifarm.porosh.data.local.db.daos.MoviesDao
import com.ifarm.porosh.data.local.db.daos.WishListDao
import com.ifarm.porosh.data.local.db.entities.Genre
import com.ifarm.porosh.data.local.db.entities.MovieGenreRef
import com.ifarm.porosh.data.local.db.entities.Movies
import com.ifarm.porosh.data.local.db.entities.WishList

@Database(
    entities = [Movies::class, Genre::class, MovieGenreRef::class, WishList::class],
    version = 1, // Increment when you change schema
    exportSchema = true
)
abstract class IMDBDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun genreDao(): GenreDao
    abstract fun movieGenreRefDao(): MovieGenreRefDao
    abstract fun wishListDao(): WishListDao

    companion object {
        const val DB_NAME = "myimdb.db"
        @Volatile private var mDbInstance: IMDBDatabase? = null

        fun getDbInstance(context: Context): IMDBDatabase {

            return mDbInstance ?: synchronized(this){
                val db = Room.databaseBuilder(
                    context.applicationContext, IMDBDatabase::class.java, DB_NAME)
                    //.fallbackToDestructiveMigration()
                    .build()
                mDbInstance = db
                db
            }

        }
    }

}
