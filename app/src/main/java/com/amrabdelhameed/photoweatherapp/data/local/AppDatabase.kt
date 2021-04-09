package com.amrabdelhameed.photoweatherapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amrabdelhameed.photoweatherapp.data.local.dao.PhotoDao
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo

@Database(
    entities = [Photo::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}