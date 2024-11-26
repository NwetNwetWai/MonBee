package com.hana.monbee.feature.customerList

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hana.domain.model.Customer
import com.hana.monbee.R
import com.hana.monbee.ui.MonBeeTheme
import com.hana.monbee.ui.Purple40

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun CustomerListScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CustomerListViewModel = hiltViewModel(),
) {
    val events = viewModel::onEvent
    val state by viewModel.states.collectAsState()

    var showExitAppDialog by remember { mutableStateOf(false) }
    var showRemoveAccDialog by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { viewModel.initialize(restartApp) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    println("CLICK:::1")
//                    viewModel.onAddClick(openScreen)
                },
                modifier = modifier.padding(16.dp),
                containerColor = Purple40,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = { showExitAppDialog = true }) {
                        Icon(Icons.Filled.ExitToApp, "Exit app")
                    }
                    IconButton(onClick = { showRemoveAccDialog = true }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.person_remove),
//                            contentDescription = "Remove account",
//                            tint = PurpleGrey40
//                        )
                    }
                }
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                when {
                    state.loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    state.error?.isNotEmpty() == true -> {
                        Text(
                            text = state.error.toString(),
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    state.customers.isNotEmpty() -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(state.customers) { customerItem ->
                                CustomerItem(
                                    customer = customerItem,
                                    onActionClick = {
                                        println("CLICK::ITEM $customerItem")
                                        events(
                                            CustomerListScreenEvent.ShowCustomerDetail(
                                                openScreen,
                                                customerItem
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }

                    else -> {
                        Text(
                            text = "No customers available.",
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

//            if (showExitAppDialog) {
//                AlertDialog(
//                    title = { Text(stringResource(R.string.sign_out_title)) },
//                    text = { Text(stringResource(R.string.sign_out_description)) },
//                    dismissButton = {
//                        Button(onClick = { showExitAppDialog = false }) {
//                            Text(text = stringResource(R.string.cancel))
//                        }
//                    },
//                    confirmButton = {
//                        Button(onClick = {
//                            viewModel.onSignOutClick()
//                            showExitAppDialog = false
//                        }) {
//                            Text(text = stringResource(R.string.sign_out))
//                        }
//                    },
//                    onDismissRequest = { showExitAppDialog = false }
//                )
//            }
//
//            if (showRemoveAccDialog) {
//                AlertDialog(
//                    title = { Text(stringResource(R.string.delete_account_title)) },
//                    text = { Text(stringResource(R.string.delete_account_description)) },
//                    dismissButton = {
//                        Button(onClick = { showRemoveAccDialog = false }) {
//                            Text(text = stringResource(R.string.cancel))
//                        }
//                    },
//                    confirmButton = {
//                        Button(onClick = {
//                            viewModel.onDeleteAccountClick()
//                            showRemoveAccDialog = false
//                        }) {
//                            Text(text = stringResource(R.string.delete_account))
//                        }
//                    },
//                    onDismissRequest = { showRemoveAccDialog = false }
//                )
//            }
//        }
//    }
//}

@Composable
fun CustomerItem(
    customer: Customer,
    onActionClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp, 8.dp)
            .clickable { onActionClick(customer.id.toString()) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text(
                text = "Name:" + customer.name.toString(),
                modifier = Modifier.padding(12.dp, 12.dp, 12.dp, 12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Email:"+ customer.email.toString(),
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesListPreview() {
    MonBeeTheme {
        CustomerListScreen({ }, { })
    }
}