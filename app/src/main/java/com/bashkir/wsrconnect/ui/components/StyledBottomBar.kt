package com.bashkir.wsrconnect.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bashkir.wsrconnect.ui.navigation.BottomBarScreen
import com.bashkir.wsrconnect.ui.theme.darkBlueColor

@Composable
fun StyledBottomBar(navController: NavController, modifier: Modifier = Modifier) = BottomNavigation(
    modifier,
    backgroundColor = darkBlueColor
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomBarScreen.bottomBarScreens.forEach { screen ->
        val selected =
            currentDestination?.route == screen.destination

        BottomNavigationItem(
            label = { Text(screen.destination) },
            icon = { Icon(painterResource(screen.icon), null) },
            selected = selected,
            onClick = {
                navController.navigate(screen.destination) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
    }
}