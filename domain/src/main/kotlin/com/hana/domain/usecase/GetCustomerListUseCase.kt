package com.hana.domain.usecase

import com.hana.domain.model.Customer
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.repo.UserRepository
import com.hana.domain.util.RepoResult
import kotlinx.coroutines.flow.Flow

class GetCustomerListUseCase (private val customerRepository: CustomerRepository) {

    suspend fun execute(): RepoResult<List<Customer>> {
        return customerRepository.getCustomerList() ?: RepoResult.Failure("Data not found")
    }
}