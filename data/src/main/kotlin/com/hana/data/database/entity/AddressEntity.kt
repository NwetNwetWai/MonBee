package com.hana.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geoId: Int
)
