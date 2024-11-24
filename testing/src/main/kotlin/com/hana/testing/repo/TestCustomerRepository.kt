package com.hana.testing.repo

import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult
import com.hana.testing.data.customersTestData

class TestCustomerRepository: CustomerRepository {
    var customers: List<Customer> = emptyList()
    override suspend fun getCustomerList(): RepoResult<List<Customer>> {
        return if (customers.isEmpty()) {
            RepoResult.Failure("Data not found")
        } else RepoResult.Success(customers)

    }
}