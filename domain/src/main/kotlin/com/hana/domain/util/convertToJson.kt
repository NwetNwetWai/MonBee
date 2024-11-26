package com.hana.domain.util

import com.google.gson.Gson
import com.hana.domain.model.Customer

fun convertToJson(customers: List<Customer>): String {
    val gson = Gson()
    return gson.toJson(customers)
}