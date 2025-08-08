package com.ifarm.porosh.data.local.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "wishlist",
    foreignKeys = [
        ForeignKey(
            entity = Movies::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("movieId")]
)
data class WishList(
    @PrimaryKey(autoGenerate = true)
    val wishlistId: Long = 0,
    val movieId: Int,

    //val addedAt: Long = System.currentTimeMillis()
)
