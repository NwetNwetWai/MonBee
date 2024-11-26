package com.hana.monbee.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hana.domain.repo.CustomerRepository
import com.hana.domain.repo.UserRepository
import com.hana.monbee.MonBeeViewModel
import com.hana.monbee.navigation.CUSTOMER_LIST_SCREEN
import com.hana.monbee.navigation.SIGN_IN_SCREEN
import com.hana.monbee.navigation.SIGN_UP_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val customerRepository: CustomerRepository
) : MonBeeViewModel(){
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            userRepository.signIn(email.value, password.value)
            openAndPopUp(CUSTOMER_LIST_SCREEN, SIGN_IN_SCREEN)
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(SIGN_UP_SCREEN, SIGN_IN_SCREEN)
    }
}