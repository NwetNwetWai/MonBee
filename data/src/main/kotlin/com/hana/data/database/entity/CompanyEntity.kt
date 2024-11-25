package com.hana.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "companies")
data class CompanyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val catchPhrase: String,
    val bs: String
)


