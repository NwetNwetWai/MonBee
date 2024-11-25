package com.hana.data.database.dao

import androidx.room.Embedded
import androidx.room.Relation
import com.hana.data.database.entity.AddressEntity
import com.hana.data.database.entity.GeoEntity

data class AddressWithGeo(
    @Embedded val address: AddressEntity,
    @Relation(
        parentColumn = "geoId",
        entityColumn = "id"
    )
    val geo: GeoEntity
)

