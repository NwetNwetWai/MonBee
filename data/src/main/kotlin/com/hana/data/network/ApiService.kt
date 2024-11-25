package com.hana.data.network

import com.hana.domain.model.Customer
import okhttp3.OkHttpClient
import retrofit2.http.GET
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.POST


interface ApiService {
    @GET("users")
    suspend fun fetchCustomerData(): List<Customer>

    @POST("posts")
    suspend fun postCustomerData()

    companion object Factory {
        fun create(httpClient: OkHttpClient): ApiService {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(httpClient)
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .build()

            return retrofit.create(ApiService::class.java)
        }

    }

}