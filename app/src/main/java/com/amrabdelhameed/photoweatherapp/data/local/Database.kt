package com.amrabdelhameed.photoweatherapp.data.local

import android.content.Context
import androidx.room.Room
import com.amrabdelhameed.photoweatherapp.BuildConfig

object Database {
    object Companion {
        fun appDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "${BuildConfig.APPLICATION_ID}.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}