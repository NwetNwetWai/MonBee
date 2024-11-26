package com.hana.monbee.feature.customerList

import com.hana.domain.model.Customer
import com.hana.domain.usecase.GetCustomerListUseCase
import com.hana.domain.util.RepoResult
import com.hana.monbee.MonBeeViewModel
import com.hana.monbee.navigation.CUSTOMER_DETAIL_SCREEN
import com.hana.monbee.navigation.SCREEN_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class CustomerListScreenState(
    val loading: Boolean = false,
    val customers: List<Customer> = listOf(),
    val error: String? = ""
)
sealed interface CustomerListScreenEvent {
    data class ShowCustomerDetail(val openScreen: (String) -> Unit, val customer: Customer): CustomerListScreenEvent
}

@HiltViewModel
class CustomerListViewModel @Inject constructor (
    private val customerListUseCase: GetCustomerListUseCase
): MonBeeViewModel() {
    private val _states by lazy { MutableStateFlow(CustomerListScreenState()) }
    val states = _states.asStateFlow()

    fun onEvent(event: CustomerListScreenEvent) = when (event) {
        is CustomerListScreenEvent.ShowCustomerDetail -> {
            showCustomerDetail(event.openScreen, event.customer)
        }
    }

    fun initialize(restartApp: (String) -> Unit) {
        _states.update { it.copy(loading = true) }
        launchCatching {
            when(val result = customerListUseCase.execute()) {
                is RepoResult.Failure -> { _states.update { it.copy(loading = false, error = it.error) }}
                is RepoResult.Success -> {_states.update { it.copy(loading = false, customers = result.data) }}
            }
        }
    }
    private fun showCustomerDetail(openScreen: (String) -> Unit, customer: Customer) {
        openScreen("$CUSTOMER_DETAIL_SCREEN?$SCREEN_ID=${customer.id}")
    }

}