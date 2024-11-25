package com.hana.data.repository

import com.hana.data.database.dao.CustomerDao
import com.hana.data.database.toEntity
import com.hana.data.network.ApiService
import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult

class CustomerRepositoryImpl (
    private val apiService: ApiService,
    private val customerDao: CustomerDao
): CustomerRepository {
    override suspend fun getCustomerList(): RepoResult<List<Customer>>{
        return RepoResult.Success(customerDao.getAllCustomers())
    }

    suspend fun syncData() {
        val apiData = apiService.fetchCustomerData()
        val entities = apiData.map { it.toEntity(it.address.id, it.company.id) }
        entities.forEach {
            customerDao.insertCustomer(it)
        }
    }
}