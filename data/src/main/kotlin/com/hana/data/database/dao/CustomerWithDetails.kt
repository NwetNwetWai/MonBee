package com.hana.data.database.dao

import androidx.room.Embedded
import androidx.room.Relation
import com.hana.data.database.entity.AddressEntity
import com.hana.data.database.entity.CompanyEntity
import com.hana.data.database.entity.CustomerEntity


data class CustomerWithDetails(
    @Embedded val customer: CustomerEntity,
    @Relation(
        parentColumn = "addressId",
        entityColumn = "id",
        entity = AddressEntity::class
    )
    val addressWithGeo: AddressWithGeo,
    @Relation(
        parentColumn = "companyId",
        entityColumn = "id"
    )
    val company: CompanyEntity
)