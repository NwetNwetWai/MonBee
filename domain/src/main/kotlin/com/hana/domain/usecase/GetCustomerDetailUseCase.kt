package com.hana.domain.usecase

import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult
import javax.inject.Inject

class GetCustomerDetailUseCase @Inject constructor(private val customerRepository: CustomerRepository) {

    suspend fun execute(customerId: Int): RepoResult<Customer> {
        return customerRepository.getCustomerDetail(customerId) ?: RepoResult.Failure("Data not found")
    }
}