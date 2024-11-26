package com.hana.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: Int,
    val name: String?,
    val username: String?,
    val email: String?,
    val phone: String?,
    val website: String?,
    val address: Address?,
    val company: Company?,
)
