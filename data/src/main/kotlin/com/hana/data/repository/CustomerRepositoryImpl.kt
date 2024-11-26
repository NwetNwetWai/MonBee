package com.hana.data.repository

import com.hana.data.database.dao.CustomerDao
import com.hana.data.database.toDomain
import com.hana.data.database.toEntity
import com.hana.data.network.APIManagerInterface
import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult
import javax.inject.Inject
import kotlin.jvm.Throws

class CustomerRepositoryImpl @Inject constructor(
    private val apiManagerInterface: APIManagerInterface,
    private val customerDao: CustomerDao,
) : CustomerRepository {

    suspend fun syncData() {
        val customers = customerDao.getAll()
        try {
            val apiData = apiManagerInterface.service().fetchCustomerData()
            if(customers.isEmpty()) {
                apiData.forEach { customerDao.insertAll(it.toEntity()) }
            } else  {
                apiData.forEach { customerDao.updateCustomers(it.toEntity()) }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getCustomerList(): RepoResult<List<Customer>> {
        syncData()
        val customers = customerDao.getAll()
        return RepoResult.Success(customers.map { it.toDomain()})
    }

    override suspend fun getCustomerDetail(customerId: Int): RepoResult<Customer> {
        val customer = customerDao.getCustomer(customerId)
        return RepoResult.Success(customer.toDomain())

    }
}