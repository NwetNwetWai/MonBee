package com.hana.data.di

import com.hana.data.repository.CustomerRepositoryImpl
import com.hana.data.repository.UserRepositoryImpl
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.repo.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepo: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindCustomerRepository(customerRepo: CustomerRepositoryImpl): CustomerRepository

}