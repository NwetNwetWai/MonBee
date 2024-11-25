package com.hana.data.database

import com.hana.data.database.entity.CustomerEntity
import com.hana.domain.model.Customer

fun CustomerEntity.toDomain(): Customer =
    Customer(id, name, username, email, phone, website, address, company)

fun Customer.toEntity(): CustomerEntity =
    CustomerEntity(id = id, name = name.toString(), username = username.toString(), email = email.toString(), phone = phone.toString(), website = website.toString(), address = address, company = company)