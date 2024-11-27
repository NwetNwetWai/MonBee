package com.hana.monbee.feature.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hana.monbee.R
import com.hana.monbee.ui.MonBeeTheme
import com.hana.monbee.ui.components.ErrorMsgDialog

@Composable

fun SignInScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
) {
    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val events = viewModel::onEvent
    val state by viewModel.states.collectAsState()


    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            state.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            state.error.isNotEmpty() -> {
                ErrorMsgDialog(
                    onDismissRequest = {
                       events(SignInEvent.Dismiss)
                    },
                    onConfirmation = {
                        events(SignInEvent.Dismiss)
                    },
                    dialogTitle = "Failed",
                    dialogText = state.error,
                    icon = Icons.Default.Warning,
                )
            }

            state.success -> {
                events(SignInEvent.GoToNext(openAndPopUp))
            }
        }
        Image(
            painter = painterResource(id = R.mipmap.auth_image),
            contentDescription = "Auth image",
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        OutlinedTextField(
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
                .border(
                    BorderStroke(width = 2.dp, color = Color.Blue),
//                    shape = RoundedCornerShape(50)
                ),
            value = email.value,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = { Text("email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
        )

        OutlinedTextField(
            singleLine = true,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp)
                .border(
                    BorderStroke(width = 2.dp, color = Color.Blue),
//                    shape = RoundedCornerShape(50)
                ),
            value = password.value,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = { Text("password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Email") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        Button(
            onClick = { events(SignInEvent.SignInClick) },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        ) {
            Text(
                text = "Sign In",
//                text = stringResource(R.string.sign_in),
                fontSize = 16.sp,
                modifier = modifier.padding(0.dp, 6.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

//        TextButton(onClick = { viewModel.onSignUpClick(openAndPopUp) }) {
//            Text(
//                text = "Sign Up",
////                text = stringResource(R.string.sign_up_description),
//                fontSize = 16.sp
//            )
//        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AuthPreview() {
    MonBeeTheme() {
        SignInScreen({ _, _ -> })
    }
}