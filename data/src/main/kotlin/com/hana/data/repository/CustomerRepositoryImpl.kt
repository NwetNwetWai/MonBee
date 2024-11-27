package com.hana.data.repository

import android.content.Context
import android.util.Log
import com.hana.data.database.dao.CustomerDao
import com.hana.data.database.toDomain
import com.hana.data.database.toEntity
import com.hana.data.network.APIManagerInterface
import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val apiManagerInterface: APIManagerInterface,
    private val customerDao: CustomerDao,
) : CustomerRepository {

    suspend fun syncData() {
        val customers = customerDao.getAll()
        val apiData =
             apiManagerInterface.service().fetchCustomerData()

            if(customers.isEmpty()) {
                apiData.forEach { customerDao.insertAll(it.toEntity()) }
            } else  {
                apiData.forEach { customerDao.updateCustomers(it.toEntity()) }
            }

    }

    override suspend fun getCustomerList(): RepoResult<List<Customer>> {
        try {
            syncData()
        } catch (_: Exception) {
            println("Exception")
        }
        val customers = customerDao.getAll()
        return RepoResult.Success(customers.map { it.toDomain()})
    }

    override suspend fun getCustomerDetail(customerId: Int): RepoResult<Customer> {
        val customer = customerDao.getCustomer(customerId)
        return RepoResult.Success(customer.toDomain())

    }

    override suspend fun generateJson(context: Context, customers: String) {
        val fileName = "customers"
        try {
            val directory = context.getDir("data", Context.MODE_PRIVATE)
            if (!directory.exists()) {
                directory.mkdirs()
            }
            val file = File(directory, "$fileName.json")
            withContext(Dispatchers.IO) {
                BufferedWriter(FileWriter(file)).use { writer ->
                    writer.write(customers)
                }
            }

            Log.d("FileSave", "File saved successfully at: ${file.absolutePath}")
        } catch (e: Exception) {
            Log.e("FileSaveError", "Error saving file: ${e.message}", e)
        }
    }

    override suspend fun saveNewCustomer(customer: Customer) : RepoResult<List<Customer>> {
        try {
            println("CUSTOMER::TO:SAVE$customer")
            customerDao.insertAll(customer.toEntity())
            println("CUSTOMER::DB:: ${customerDao.getAll()}")
            return RepoResult.Success(customerDao.getAll().map { it.toDomain()})
        } catch (e: Exception) {
            println("Error $e")
            return RepoResult.Failure("Error")
        }
    }
}