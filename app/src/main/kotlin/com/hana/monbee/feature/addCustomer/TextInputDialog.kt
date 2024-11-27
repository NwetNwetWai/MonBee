package com.hana.monbee.feature.addCustomer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hana.domain.model.Customer

@Composable
fun TextInputDialog(
    id: Int = -1,
    title: String = "Enter Text",
    initialText: String = "",
    onDismiss: () -> Unit,
    onConfirm: (Customer) -> Unit
) {
    var name by remember { mutableStateOf(initialText) }
    var phone by remember { mutableStateOf(initialText) }
    var email by remember { mutableStateOf(initialText) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = title) },
        text = {
            Column {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
//                        placeholder = { Text("Name") },
                        singleLine = true
                    )
                    TextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Phone") },
//                        placeholder = { Text("Phone") },
                        singleLine = true
                    )
                    TextField(
                        value = email ,
                        onValueChange = { email = it },
                        label = { Text("Email") },
//                        placeholder = { Text("Email") },
                        singleLine = true
                    )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(Customer(
                    id = id,
                    name = name,
                    username = name,
                    phone = phone,
                    email = email,
                    address = null,
                    company = null,
                    website = null

                ))
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}