package com.hana.monbee.feature.customerList

import android.content.Context
import com.hana.domain.model.Customer
import com.hana.domain.usecase.GenerateJsonUseCase
import com.hana.domain.usecase.GetCustomerListUseCase
import com.hana.domain.usecase.SaveNewCustomerUseCase
import com.hana.domain.usecase.UploadDataUseCase
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
    val error: String? = "",
    val success: String? = ""
)
sealed interface CustomerListScreenEvent {
    data class ShowCustomerDetail(val openScreen: (String) -> Unit, val customer: Customer): CustomerListScreenEvent
    data class GenerateJson(val context: Context, val customers: List<Customer>) : CustomerListScreenEvent
    data class SaveNewCustomer(val newCustomer: Customer) : CustomerListScreenEvent
    data object Dismiss: CustomerListScreenEvent
    data object UploadData: CustomerListScreenEvent
}

@HiltViewModel
class CustomerListViewModel @Inject constructor (
    private val customerListUseCase: GetCustomerListUseCase,
    private val generateJsonUseCase: GenerateJsonUseCase,
    private val saveNewCustomerUseCase: SaveNewCustomerUseCase,
    private val uploadDataUseCase: UploadDataUseCase
): MonBeeViewModel() {
    private val _states by lazy { MutableStateFlow(CustomerListScreenState()) }
    val states = _states.asStateFlow()

    fun onEvent(event: CustomerListScreenEvent) = when (event) {
        is CustomerListScreenEvent.ShowCustomerDetail -> {
            showCustomerDetail(event.openScreen, event.customer)
        }

        is CustomerListScreenEvent.GenerateJson -> {
            generateJson(event.context, event.customers)
        }

        CustomerListScreenEvent.Dismiss -> {
            _states.update { it.copy(error = null) }
            _states.update { it.copy(success = null) }
        }

        is CustomerListScreenEvent.SaveNewCustomer -> {
            saveNewCustomer(event.newCustomer)
        }

        CustomerListScreenEvent.UploadData -> {
            uploadData()
        }
    }

    private fun uploadData() {
        _states.update { it.copy(loading = true) }
        launchCatching {
            when(val result = uploadDataUseCase.execute() ){
                is RepoResult.Failure -> {_states.update { it.copy(loading = false, error = result.error) }}
                is RepoResult.Success -> {_states.update { it.copy(loading = false, success = "Uploaded") }}
            }
        }
    }

    private fun saveNewCustomer(newCustomer: Customer) {
        println("CUSTOMER:::$newCustomer")
        _states.update { it.copy(loading = true) }
        launchCatching {
         when(val result = saveNewCustomerUseCase.execute(newCustomer) ){
             is RepoResult.Failure -> {_states.update { it.copy(loading = false, error = result.error) }}
             is RepoResult.Success -> {_states.update { it.copy(loading = false, success = "Saved", customers = result.data) }}
         }
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


    private fun generateJson(context: Context, customers: List<Customer>) {
        launchCatching {
            generateJsonUseCase.execute(context, customers)
        }

    }

}