package com.bashkir.wsrconnect.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bashkir.wsrconnect.ConnectViewModel
import com.bashkir.wsrconnect.ui.components.StyledBottomBar
import com.bashkir.wsrconnect.ui.navigation.CreateBottomBarNavHost

@Composable
fun BottomBarScreen(
    mainNavController: NavController,
    bottomNavController: NavHostController,
    viewModel: ConnectViewModel
) = Scaffold(
    bottomBar = { StyledBottomBar(navController = bottomNavController) }
) {
    Box(Modifier.padding(it)) {
        CreateBottomBarNavHost(
            bottomNavController = bottomNavController,
            mainNavController,
            viewModel
        )
    }
}