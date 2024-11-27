package com.hana.monbee.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.repo.UserRepository
import com.hana.domain.usecase.SignInUseCase
import com.hana.domain.util.RepoResult
import com.hana.monbee.MonBeeViewModel
import com.hana.monbee.feature.customerList.CustomerListScreenEvent
import com.hana.monbee.feature.customerList.CustomerListScreenState
import com.hana.monbee.navigation.CUSTOMER_LIST_SCREEN
import com.hana.monbee.navigation.SIGN_IN_SCREEN
import com.hana.monbee.navigation.SIGN_UP_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SignInState(
    val error: String = "",
    val loading: Boolean = false,
    val success: Boolean = false
)

sealed interface SignInEvent {
    data object SignInClick: SignInEvent
    data object Dismiss : SignInEvent
    data class GoToNext(val openAndPopUp: (String, String) -> Unit): SignInEvent
}


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : MonBeeViewModel(){
    private val _states by lazy { MutableStateFlow(SignInState()) }
    val states = _states.asStateFlow()

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onEvent(event: SignInEvent) = when (event) {
       is SignInEvent.GoToNext -> { goToNext(event.openAndPopUp)  }
        SignInEvent.SignInClick -> onSignInClick()
        SignInEvent.Dismiss -> _states.update { it.copy(error = "") }
    }

    private fun goToNext(openAndPopUp: (String, String) -> Unit){
        openAndPopUp(CUSTOMER_LIST_SCREEN, SIGN_IN_SCREEN)
    }

    private fun onSignInClick() {
        launchCatching {
           when(val result = signInUseCase.execute(email.value, password.value)) {
                is RepoResult.Failure -> {_states.update { it.copy(loading = false, error = result.error) } }
                is RepoResult.Success -> {_states.update { it.copy(loading = false, success = true) }}
            }
        }

    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(SIGN_UP_SCREEN, SIGN_IN_SCREEN)
    }
}