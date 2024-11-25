package com.hana.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hana.data.database.dao.CustomerDao
import com.hana.data.database.entity.CustomerEntity

@Database(
    entities = [CustomerEntity::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
}
