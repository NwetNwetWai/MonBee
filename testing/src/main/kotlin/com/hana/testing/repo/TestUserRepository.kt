package com.hana.testing.repo

import com.hana.domain.repo.UserRepository
import com.hana.domain.util.RepoResult

class TestUserRepository : UserRepository {
    var shouldReturnNull = false
    override suspend fun signIn(email: String, password: String): RepoResult<String>? {
        return if (shouldReturnNull) {
            null
        } else {
            RepoResult.Success("SignIn Success")
        }
    }
}