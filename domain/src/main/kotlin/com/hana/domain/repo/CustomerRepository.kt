package com.hana.domain.repo

import com.hana.domain.model.Customer
import com.hana.domain.util.RepoResult
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun getCustomerList(): RepoResult<List<Customer>>
    suspend fun getCustomerDetail(customerId: Int): RepoResult<Customer>
}