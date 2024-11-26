package com.hana.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hana.domain.repo.UserRepository
import com.hana.domain.util.RepoResult
import okio.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository{
    override suspend fun signIn(email: String, password: String): RepoResult<String> {
           try {
               Firebase.auth.signInWithEmailAndPassword(email, password)
               return RepoResult.Success("Authorized.")
           } catch (e: Exception) {
               val errorMessage = when(e) {
                   is IOException  -> "Unable to connect Internet."
                   else -> {"UnAuthorized"}
               }
               return RepoResult.Failure(errorMessage)
           }
    }
}