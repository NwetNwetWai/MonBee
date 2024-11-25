package com.hana.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val companyName: String?,
    val catchPhrase: String?,
    val bs: String?
)


