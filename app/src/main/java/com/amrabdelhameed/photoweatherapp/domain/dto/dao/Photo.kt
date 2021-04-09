package com.amrabdelhameed.photoweatherapp.domain.dto.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
class Photo(
    var path: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)