package com.hana.domain.util

sealed class RepoResult<out T> {
    data class Success<out T>(val data: T) : RepoResult<T>()
    data class Failure(val error: String) : RepoResult<Nothing>()
}