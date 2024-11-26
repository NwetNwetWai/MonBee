package com.hana.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.hana.data.database.entity.CustomerEntity
import com.hana.domain.model.Customer

@Dao
interface CustomerDao {
    @Insert
    suspend fun insertAll(vararg customers: CustomerEntity)

    @Update
    suspend fun updateCustomers(vararg customers: CustomerEntity)

    @Delete
    suspend fun delete(customer: CustomerEntity)

    @Query("SELECT * FROM customers")
    suspend fun getAll(): List<CustomerEntity>

    @Query("SELECT * FROM customers WHERE id = :customerId")
    suspend fun getCustomer(customerId: Int): CustomerEntity

}
