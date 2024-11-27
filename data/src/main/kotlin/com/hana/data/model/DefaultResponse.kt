package com.hana.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultResponse(

    @SerialName("data") var message: String? = null,
    @SerialName("error") val error: Boolean? = null,
    @SerialName("status") val status: Int? = null

)