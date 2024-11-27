package com.hana.domain.repo

import com.hana.domain.model.Customer
import com.hana.domain.util.RepoResult

interface UserRepository {

    suspend fun signIn(email: String, password: String): RepoResult<String>?


}