package com.hana.data.network

import com.hana.domain.model.Customer

class ApiServiceImpl : ApiService {
    override suspend fun fetchCustomerData(): List<Customer> {
        return listOf()
    }
}