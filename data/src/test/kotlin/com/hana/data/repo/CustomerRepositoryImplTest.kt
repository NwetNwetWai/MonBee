package com.hana.data.repo

import com.hana.data.database.dao.CustomerDao
import com.hana.data.database.entity.CustomerEntity
import com.hana.data.database.toEntity
import com.hana.data.network.ApiService
import com.hana.data.repository.CustomerRepositoryImpl
import com.hana.domain.util.RepoResult
import com.hana.testing.data.customersTestData
import com.hana.testing.util.MainDispatcherRule
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CustomerRepositoryImplTest {

    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private lateinit var repository: CustomerRepositoryImpl
    private val mockApiService = mockk<ApiService>()
    private val mockDao = mockk<CustomerDao>()
    private lateinit var customerTestEntity: List<CustomerEntity>

    @Before
    fun setUp() {
        repository = CustomerRepositoryImpl(mockApiService, mockDao)
    }

    @Test
    fun `fetch data from API and save to database`() = runTest {
        val apiData = customersTestData
        customerTestEntity.apply {
            customersTestData.forEach { it.toEntity(it.address.id, it.company.id) }
        }
        println(customerTestEntity)

        val dbData = customerTestEntity

            coEvery { mockApiService.fetchCustomerData() } returns apiData
            coEvery { mockDao.insertCustomer(any()) } just Runs

            repository.syncData()

            coVerify { mockApiService.fetchCustomerData() }
            coVerify { mockDao.insertCustomer(dbData) }

        }

        @Test
        fun `fetch data from database when offline`() = runTest {
            val dbData = customersTestData
            coEvery { mockDao.getAllCustomers() } returns dbData

            val result = repository.getCustomerList()

            assertEquals(RepoResult.Success(dbData), result)
            coVerify { mockDao.getAllCustomers() }
        }
    }
