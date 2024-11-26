package com.hana.data.database

import androidx.room.TypeConverter
import com.hana.domain.model.Geo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GeoTypeConverter {

    @TypeConverter
    fun fromGeo(geo: Geo): String {
        return Json.encodeToString(geo) // Serialize Geo object to JSON string
    }

    @TypeConverter
    fun toGeo(value: String): Geo {
        return Json.decodeFromString(value) // Deserialize JSON string to Geo object
    }
}