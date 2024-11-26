package com.hana.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Company(
    @SerialName("name")
    val companyName: String?,
    val catchPhrase: String?,
    val bs: String?
)


