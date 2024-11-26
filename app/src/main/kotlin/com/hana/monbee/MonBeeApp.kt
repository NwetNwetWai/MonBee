package com.hana.monbee

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hana.monbee.feature.auth.SignInScreen
import com.hana.monbee.feature.customerDetails.CustomerDetailScreen
import com.hana.monbee.feature.customerList.CustomerListScreen
import com.hana.monbee.navigation.CUSTOMER_DETAIL_SCREEN
import com.hana.monbee.navigation.CUSTOMER_LIST_SCREEN
import com.hana.monbee.navigation.SCREEN_DEFAULT_ID
import com.hana.monbee.navigation.SCREEN_ID
import com.hana.monbee.navigation.SCREEN_ID_ARG
import com.hana.monbee.navigation.SIGN_IN_SCREEN
import com.hana.monbee.navigation.SIGN_UP_SCREEN
import com.hana.monbee.ui.MonBeeTheme

@Composable
fun MonBeeApp() {
    MonBeeTheme{
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            Scaffold { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SIGN_IN_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    notesGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        MonBeeAppState(navController)
    }

fun NavGraphBuilder.notesGraph(appState: MonBeeAppState) {
    composable(CUSTOMER_LIST_SCREEN) {
        CustomerListScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) }
        )
    }

    composable(
        route = "$CUSTOMER_DETAIL_SCREEN$SCREEN_ID_ARG",
        arguments = listOf(navArgument(SCREEN_ID) { defaultValue = SCREEN_DEFAULT_ID })
    ) {
        CustomerDetailScreen(
            customerId = it.arguments?.getString(SCREEN_ID) ?: SCREEN_DEFAULT_ID,
            popUpScreen = { appState.popUp() },
            restartApp = { route -> appState.clearAndNavigate(route) }
        )
    }

    composable(SIGN_IN_SCREEN) {
        SignInScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
//        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

//    composable(SPLASH_SCREEN) {
//        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
//    }
}
