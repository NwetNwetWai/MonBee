package com.hana.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hana.data.database.entity.GeoEntity

@Dao
interface GeoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeo(geo: GeoEntity): Long
}