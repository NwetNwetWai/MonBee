package com.hana.data.network

import com.hana.domain.model.Customer

interface ApiService {
    suspend fun fetchCustomerData(): List<Customer>
}