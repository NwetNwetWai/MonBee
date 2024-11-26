package com.hana.monbee.feature.customerDetails

import com.hana.domain.model.Address
import com.hana.domain.model.Company
import com.hana.domain.model.Customer
import com.hana.domain.model.Geo
import com.hana.domain.usecase.GetCustomerDetailUseCase
import com.hana.domain.util.RepoResult
import com.hana.monbee.MonBeeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class CustomerDetailScreenState(
    val loading: Boolean = false,
    val error: String? = "",
    val data: Customer = Customer(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
        address = Address(
            street = "Kulas Light",
            suite = "Apt. 556",
            city = "Gwenborough",
            zipcode = "92998-3874",
            geo = Geo(
                lat = "-37.3159",
                lng = "81.1496"
            )
        ),
        company = Company(
            companyName = "Romaguera-Crona",
            catchPhrase = "Multi-layered client-server neural-net",
            bs = "harness real-time e-markets"
        )
    ),
)

sealed interface CustomerDetailScreenEvent {

}

@HiltViewModel
class CustomerDetailViewModel @Inject constructor(
    private val customerDetailUserCase: GetCustomerDetailUseCase,
) : MonBeeViewModel() {
    private val _states by lazy { MutableStateFlow(CustomerDetailScreenState()) }
    val states = _states.asStateFlow()

//    fun onEvent(event: CustomerDetailScreenEvent) = when (event) {
//
//    }

    fun initialize(customerId: String, restartApp: (String) -> Unit) {

        _states.update { it.copy(loading = true) }
        launchCatching {
            when (val result = customerDetailUserCase.execute(customerId.toInt())) {
                is RepoResult.Failure -> {
                    _states.update { it.copy(loading = false, error = it.error) }
                }

                is RepoResult.Success -> {
                    _states.update { it.copy(loading = false, data = result.data) }
                }
            }
        }
    }
}