package com.hana.domain.repo

import com.hana.domain.model.Customer
import com.hana.domain.util.RepoResult

interface CustomerRepository {
    suspend fun getCustomerList(): RepoResult<List<Customer>>
}