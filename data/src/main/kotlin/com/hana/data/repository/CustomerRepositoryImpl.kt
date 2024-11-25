package com.hana.data.repository

import com.hana.data.database.dao.CustomerDao
import com.hana.data.database.toDomain
import com.hana.data.database.toEntity
import com.hana.data.network.ApiService
import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult

class CustomerRepositoryImpl(
    private val apiService: ApiService,
    private val customerDao: CustomerDao,
) : CustomerRepository {


//    override suspend fun getCustomerList(): RepoResult<List<Customer>> {
//        // Fetch all customers with address and geo details from the database
//        val customers = customerDao.getAllCustomers()
//        println("DATA:::$customers")
//
//        // Map geo for each customer (assuming you want to get geo for each individual address)
//        val geoList = customers.map { it.addressWithGeo?.geo?.toDomain()   }
//        println("Geo Data:::$geoList")
//
//        // Map address and company to domain models for each customer
//        val customerList = customers.mapIndexed { index, customer ->
//            val address = customer.addressWithGeo?.address?.toDomain(geoList[index])  // Handle nullability
//            val company = customer.company?.toDomain()  // Assuming company is non-null
//            customer.customer.toDomain(address, company)
////            val address = customer.addressWithGeo?.address?.toDomain(geoList[index]) // Transform address with geo
////            val company = customer.company.toDomain() // Assuming company also has a `toDomain()` function
////            customer.customer.toDomain(address, company) // Transform the whole CustomerEntity to Customer
//        }
//        println("Customer Data:::$customerList")

        // Return the transformed list of domain models
//        return RepoResult.Success(customerList)
//    }



    suspend fun syncData() {
        val apiData = apiService.fetchCustomerData()
        apiData.forEach { customerDao.insertAll(it.toEntity()) }

//        val entities = apiData.map { it.toEntity(it.address?.id ?: 0, it.company?.id ?:0) }
//       entities.forEach {  customerDao.insertCustomer(it) }


    }

    override suspend fun getCustomerList(): RepoResult<List<Customer>> {
        val customers = customerDao.getAll()
        return RepoResult.Success(customers.map { it.toDomain()})
    }
}