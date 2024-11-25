package com.hana.data.database

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
    fun fromCustomString(value: String?): String? {
        // Add any necessary preprocessing for saving, e.g., escaping special characters
        return value?.replace("-", "_")?.replace("[", "(")?.replace("]", ")")
    }

    @TypeConverter
    fun toCustomString(value: String?): String? {
        // Revert preprocessing back to the original format when retrieving
        return value?.replace("_", "-")?.replace("(", "[")?.replace(")", "]")
    }
}
