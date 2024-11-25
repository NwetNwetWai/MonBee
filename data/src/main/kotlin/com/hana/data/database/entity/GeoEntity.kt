package com.hana.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geo")
data class GeoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val lat: String,
    val lng: String
)


