package com.hana.data.repository

import com.hana.data.database.dao.CustomerDao
import com.hana.data.database.toDomain
import com.hana.data.database.toEntity
import com.hana.data.network.APIManagerInterface
import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val apiManagerInterface: APIManagerInterface,
    private val customerDao: CustomerDao,
) : CustomerRepository {

    override suspend fun syncData() {
        val apiData = apiManagerInterface.service().fetchCustomerData()
        apiData.forEach { customerDao.insertAll(it.toEntity()) }
    }

    override suspend fun getCustomerList(): RepoResult<List<Customer>> {
        val customers = customerDao.getAll()
        return RepoResult.Success(customers.map { it.toDomain()})
    }
}