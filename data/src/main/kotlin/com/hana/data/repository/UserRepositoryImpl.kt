package com.hana.data.repository

import com.hana.domain.repo.UserRepository
import com.hana.domain.util.RepoResult
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository{
    override suspend fun signIn(email: String, password: String): RepoResult<String>? {
        return RepoResult.Success("Success")
    }
}