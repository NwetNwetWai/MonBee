package com.hana.domain.usecase

import android.content.Context
import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult
import com.hana.domain.util.convertToJson
import javax.inject.Inject

class GenerateJsonUseCase @Inject constructor(val customerRepository: CustomerRepository) {

    suspend fun execute(context: Context, customers: List<Customer>) {
        val jsonData = convertToJson(customers)
        return customerRepository.generateJson(context, jsonData)
    }
}