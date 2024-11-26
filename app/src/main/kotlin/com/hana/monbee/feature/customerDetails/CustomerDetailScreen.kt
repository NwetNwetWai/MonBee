package com.hana.monbee.feature.customerDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hana.monbee.ui.MonBeeTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomerDetailScreen(
    customerId: String,
    popUpScreen: () -> Unit,
    restartApp: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CustomerDetailViewModel = hiltViewModel(),
) {
//    val events = viewModel::onEvent
    val state by viewModel.states.collectAsState()

    LaunchedEffect(Unit) { viewModel.initialize(customerId, restartApp) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopAppBar(
            title = { Text("Customer Detail") },
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
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

                state.error?.isNotEmpty() == true -> {
                    Text(
                        text = state.error.toString(),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                }

                state.data.id != 0 -> {
                    val customer = state.data
                    val customerDetails = mutableListOf<Pair<String, String>>().apply {
                        add("UserName:" to state.data.username.toString())
                        add("Email:" to state.data.email.toString())
                        add("Phone:" to state.data.name.toString())
                        add("Address:" to (state.data.address?.street.toString() + "," + state.data.address?.city.toString()))
                    }
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                        modifier = Modifier.padding(16.dp),

                        ) {
                        Text(
                            text = "Name: " + state.data.name.toString(),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.padding(12.dp)
                        )
//                        Spacer(Modifier.padding(bottom = 12.dp))
                        HorizontalDivider(thickness = 1.dp)
                        Spacer(Modifier.padding(bottom = 12.dp))

                        for (item in customerDetails) {
                            CustomerItemBox(item)
                        }
//                        Spacer(Modifier.padding(bottom = 12.dp))
                        HorizontalDivider(thickness = 1.dp)
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

@Composable
fun CustomerItemBox(invoiceItem: Pair<String, String>) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = invoiceItem.first,

            )
        Text(
            text = invoiceItem.second
        )
    }
    Spacer(Modifier.padding(bottom = 12.dp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotePreview() {
    MonBeeTheme() {
        CustomerDetailScreen(customerId = "1", {}, {})
    }
}