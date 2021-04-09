package com.amrabdelhameed.photoweatherapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amrabdelhameed.photoweatherapp.domain.dto.dao.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Delete
    suspend fun delete(photo: Photo)

    @Query("SELECT * FROM photos")
    fun getPhotos(): LiveData<List<Photo>>
}