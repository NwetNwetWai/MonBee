package com.hana.domain.repo

import com.hana.domain.model.Customer
import com.hana.domain.util.RepoResult
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun syncData()
    suspend fun getCustomerList(): RepoResult<List<Customer>>
}