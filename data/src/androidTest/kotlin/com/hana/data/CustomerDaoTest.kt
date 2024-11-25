package com.hana.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.hana.data.database.AppDatabase
import com.hana.data.database.dao.CustomerDao
import com.hana.data.database.entity.CustomerEntity
import com.hana.data.database.toEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class CustomerDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: CustomerDao

    @Before
    fun setUp() {
        println("Setting up the database...")
        // Initialize the in-memory database before each test
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.customerDao()
        println("Database initialized successfully.")
    }

    @After
    fun tearDown() {
        println("Tearing down the database...")
        // Close the database after each test
        if (::database.isInitialized) {
            database.close()
            println("Database closed.")
        } else {
            println("Database was not initialized.")
        }
    }

    @Test
    fun insert_and_retrieve_data() = runTest {
//        val customerTestEntity: List<CustomerEntity> = customerDaoTestData.map {
//            it.toEntity(it.address?.id ?: 0, it.company?.id ?: 0)
//        }
//        customerTestEntity.forEach { dao.insertCustomer(it) }



        val result = dao.getAllCustomers().first()
        assertEquals(customerTestEntity, result)
    }
}
