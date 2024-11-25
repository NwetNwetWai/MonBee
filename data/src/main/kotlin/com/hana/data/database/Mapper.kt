package com.hana.data.database

import com.hana.data.database.entity.AddressEntity
import com.hana.data.database.entity.CompanyEntity
import com.hana.data.database.entity.CustomerEntity
import com.hana.data.database.entity.GeoEntity
import com.hana.domain.model.Address
import com.hana.domain.model.Company
import com.hana.domain.model.Customer
import com.hana.domain.model.Geo


fun GeoEntity.toDomain(): Geo = Geo(id, lat, lng)

fun AddressEntity.toDomain(geo: Geo): Address = Address(id, street, suite, city, zipcode, geo)

fun CompanyEntity.toDomain(): Company = Company(id, name, catchPhrase, bs)

fun CustomerEntity.toDomain(address: Address, company: Company): Customer =
    Customer(id, name, username, email, phone, website, address, company)

fun Geo.toEntity(): GeoEntity = GeoEntity(id = id,lat = lat, lng = lng)

fun Address.toEntity(geoId: Int): AddressEntity =
    AddressEntity(id = id, street = street, suite = suite, city = city, zipcode = zipcode, geoId = geoId)

fun Company.toEntity(): CompanyEntity =
    CompanyEntity(id = id, name = name, catchPhrase = catchPhrase, bs = bs)

fun Customer.toEntity(addressId: Int, companyId: Int): CustomerEntity =
    CustomerEntity(id = id, name = name, username = username, email = email, phone = phone, website = website, addressId = addressId, companyId = companyId)
