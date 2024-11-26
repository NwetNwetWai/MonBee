package com.hana.domain

import com.hana.domain.usecase.GetCustomerListUseCase
import com.hana.domain.util.RepoResult
import com.hana.testing.data.customersTestData
import com.hana.testing.repo.TestCustomerRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCustomerListUseCaseTest {
    private val testCustomerRepository = TestCustomerRepository()
    private val getCustomerUseCase = GetCustomerListUseCase(testCustomerRepository)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Test
    fun `should return Failure when data is empty`() = testScope.runTest {
        testCustomerRepository.customers = emptyList()

        val result = getCustomerUseCase.execute()
        assertEquals(RepoResult.Failure("Data not found"), result)
    }

    @Test
    fun `should return Success when data exist`() = testScope.runTest {
        testCustomerRepository.customers = customersTestData
        val result = getCustomerUseCase.execute()
        assertEquals(RepoResult.Success(customersTestData), result)
    }
}