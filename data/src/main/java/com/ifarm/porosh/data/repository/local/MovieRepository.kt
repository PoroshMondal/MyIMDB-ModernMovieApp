package com.ifarm.porosh.data.repository.local

interface MovieRepository {
    suspend fun isDataStored()
}