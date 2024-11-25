package com.hana.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hana.data.database.entity.CustomerEntity
import com.hana.domain.model.Customer
import kotlinx.coroutines.flow.Flow


@Dao
interface CustomerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customers: CustomerEntity)

    @Transaction
    @Query("SELECT * FROM customers WHERE id = :cID")
    suspend fun getCustomerWithDetails(cID: Int): CustomerWithDetails

    @Transaction
    @Query("SELECT * FROM customers")
    suspend fun getAllCustomers(): List<Customer>
}