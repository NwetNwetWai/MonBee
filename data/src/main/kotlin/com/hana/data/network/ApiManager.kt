package com.hana.data.network

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIManager @Inject constructor(
    private val apiService: ApiService,
) : APIManagerInterface {

    override fun service(): ApiService {
        return apiService
    }

    companion object {
        private val TAG: String = APIManager::class.java.simpleName
    }
}
