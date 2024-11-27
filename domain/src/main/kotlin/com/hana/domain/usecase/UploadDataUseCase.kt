package com.hana.domain.usecase

import com.hana.domain.repo.CustomerRepository
import com.hana.domain.util.RepoResult
import javax.inject.Inject

class UploadDataUseCase @Inject constructor(private val customerRepository: CustomerRepository) {

    suspend fun execute() : RepoResult<String> {
        return customerRepository.uploadCustomer()
    }
}