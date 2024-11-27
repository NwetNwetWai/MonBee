package com.hana.domain.usecase

import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.repo.UserRepository
import com.hana.domain.util.RepoResult
import javax.inject.Inject

class SaveNewCustomerUseCase @Inject constructor(private val customerRepository: CustomerRepository) {
    suspend fun execute(customer: Customer) : RepoResult<String>{
       return customerRepository.saveNewCustomer(customer)
    }
}