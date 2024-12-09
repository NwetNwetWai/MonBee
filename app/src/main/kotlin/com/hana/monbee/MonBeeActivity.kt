package com.hana.monbee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hana.monbee.ui.MonBeeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MonBeeActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonBeeTheme {
                MonBeeApp()
            }
        }
    }
}