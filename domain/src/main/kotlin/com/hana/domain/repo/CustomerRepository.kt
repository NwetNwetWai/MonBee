package com.hana.domain.repo

import android.content.Context
import com.hana.domain.model.Customer
import com.hana.domain.util.RepoResult

interface CustomerRepository {
    suspend fun getCustomerList(): RepoResult<List<Customer>>
    suspend fun getCustomerDetail(customerId: Int): RepoResult<Customer>
    suspend fun generateJson(context: Context, customers: String)
    suspend fun saveNewCustomer(customer: Customer): RepoResult<List<Customer>>
    suspend fun uploadCustomer(): RepoResult<String>
}