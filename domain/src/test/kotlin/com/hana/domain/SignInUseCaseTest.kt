package com.hana.domain

import com.hana.domain.usecase.SignInUseCase
import com.hana.domain.util.RepoResult
import com.hana.testing.repo.TestUserRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SignInUseCaseTest {
    private val testUserRepository = TestUserRepository()
    private val signInUseCase = SignInUseCase(testUserRepository)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Test
    fun `should return failure when email is empty`() = testScope.runTest {
        val result = signInUseCase.execute("", "password123")
        assertTrue(result is RepoResult.Failure)
        assertEquals("Mail Address is required.", (result as RepoResult.Failure).error)
    }

    @Test
    fun `should return failure when password is empty`()= testScope.runTest {
        val result = signInUseCase.execute("user@example.com", "")
        assertTrue(result is RepoResult.Failure)
        assertEquals("Password is required.", (result as RepoResult.Failure).error)
    }

    @Test
    fun `should return failure when email is invalid`()= testScope.runTest {
        val result = signInUseCase.execute("userexample.com", "password123")
        assertTrue(result is RepoResult.Failure)
        assertEquals("Invalid email format.", (result as RepoResult.Failure).error)
    }

    @Test
    fun `should return unauthorized when signIn fails`()= testScope.runTest {
        testUserRepository.shouldReturnNull = true

        val result = signInUseCase.execute("user@example.com", "password123")
        assertTrue(result is RepoResult.Failure)
        assertEquals("Unauthorized", (result as RepoResult.Failure).error)
    }

    @Test
    fun `should sign in successfully when credentials are valid`()= testScope.runTest {
        testUserRepository.shouldReturnNull = false

        val result = signInUseCase.execute("user@example.com", "password123")
        assertTrue(result is RepoResult.Success)
        assertEquals("SignIn Success", (result as RepoResult.Success).data)
    }
}