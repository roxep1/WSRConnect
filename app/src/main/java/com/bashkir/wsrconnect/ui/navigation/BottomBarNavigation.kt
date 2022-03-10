package com.bashkir.wsrconnect.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bashkir.wsrconnect.ConnectViewModel
import com.bashkir.wsrconnect.R
import com.bashkir.wsrconnect.ui.navigation.BottomBarScreen.*
import com.bashkir.wsrconnect.ui.screens.bottom_bar.ChatScreen
import com.bashkir.wsrconnect.ui.screens.bottom_bar.ProfileScreen
import com.bashkir.wsrconnect.ui.screens.bottom_bar.SettingsScreen

sealed class BottomBarScreen(val destination: String, @DrawableRes val icon: Int) {
    companion object {
        val bottomBarScreens = listOf(ChatScreen, ProfileScreen, SettingsScreen)
    }

    object ChatScreen : BottomBarScreen("Chats", R.drawable.ic_chats)
    object ProfileScreen : BottomBarScreen("Profile", R.drawable.ic_profile)
    object SettingsScreen : BottomBarScreen("Settings", R.drawable.ic_settings)
}


@Composable
fun CreateBottomBarNavHost(bottomNavController: NavHostController, mainNavController: NavController, viewModel: ConnectViewModel) = NavHost(
    navController = bottomNavController,
    startDestination = ChatScreen.destination
) {
    composable(ChatScreen.destination){
        ChatScreen()
    }

    composable(ProfileScreen.destination){
        ProfileScreen(viewModel, mainNavController)
    }

    composable(SettingsScreen.destination){
        SettingsScreen()
    }
}