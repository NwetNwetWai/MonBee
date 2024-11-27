package com.hana.domain.usecase

import com.hana.domain.repo.UserRepository
import com.hana.domain.util.RepoResult
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun execute(email: String, password: String): RepoResult<String> {
        return when {
            email.isEmpty() -> RepoResult.Failure("Mail Address is required.")
            password.isEmpty() -> RepoResult.Failure("Password is required.")
            email.isNotEmpty() && !email.contains("@") -> RepoResult.Failure("Invalid email format.")
            else -> userRepository.signIn(email, password) ?: RepoResult.Failure("Unauthorized")
        }
    }
}