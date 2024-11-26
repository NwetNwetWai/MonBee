package com.hana.testing.repo

import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult

class TestCustomerRepository: CustomerRepository {
    var customers: List<Customer> = emptyList()

    override suspend fun getCustomerList(): RepoResult<List<Customer>> {
        return if (customers.isEmpty()) {
            RepoResult.Failure("Data not found")
        } else RepoResult.Success(customers)

    }

    override suspend fun getCustomerDetail(customerId: Int): RepoResult<Customer> {

        return RepoResult.Success(customers[customerId])
    }
}