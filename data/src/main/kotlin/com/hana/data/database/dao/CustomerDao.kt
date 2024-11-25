package com.hana.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hana.data.database.entity.CustomerEntity
import com.hana.domain.model.Customer

@Dao
interface CustomerDao {
    @Insert
    fun insertAll(vararg customers: CustomerEntity)

    @Delete
    fun delete(customer: CustomerEntity)

    @Query("SELECT * FROM customers")
    fun getAll(): List<CustomerEntity>
}
