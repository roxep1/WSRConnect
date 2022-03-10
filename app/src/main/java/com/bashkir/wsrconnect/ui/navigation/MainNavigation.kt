package com.bashkir.wsrconnect.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bashkir.wsrconnect.ConnectViewModel
import com.bashkir.wsrconnect.ui.navigation.WSRConnectScreen.*
import com.bashkir.wsrconnect.ui.screens.BottomBarScreen
import com.bashkir.wsrconnect.ui.screens.LaunchScreen
import com.bashkir.wsrconnect.ui.screens.SignInScreen
import com.bashkir.wsrconnect.ui.screens.SignUpScreen

sealed class WSRConnectScreen(val destination: String){
    @SuppressLint("CustomSplashScreen")
    object LaunchScreen : WSRConnectScreen("launch")
    object SignInScreen : WSRConnectScreen("sign_in")
    object SignUpScreen : WSRConnectScreen("sign_up")
    object MainScreen : WSRConnectScreen("bottom_bar")
}

@Composable
fun CreateMainNavHost(navController: NavHostController, viewModel: ConnectViewModel) = NavHost(
    navController = navController,
    startDestination = LaunchScreen.destination
){
    composable(LaunchScreen.destination){
        LaunchScreen(navController)
    }

    composable(SignInScreen.destination){
        SignInScreen(navController, viewModel)
    }

    composable(SignUpScreen.destination){
        SignUpScreen(navController, viewModel)
    }

    composable(MainScreen.destination){
        BottomBarScreen(navController, rememberNavController(), viewModel)
    }
}

