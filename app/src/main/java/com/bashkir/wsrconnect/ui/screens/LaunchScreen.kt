package com.bashkir.wsrconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bashkir.wsrconnect.R
import com.bashkir.wsrconnect.ui.components.Background
import com.bashkir.wsrconnect.ui.navigation.WSRConnectScreen
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme.dimens
import kotlinx.coroutines.delay

@Composable
fun LaunchScreen(navController: NavController) = ConstraintLayout(Modifier.fillMaxSize()) {
    val (icon, text) = createRefs()

    Background(R.drawable.ic_background)

    Image(
        painterResource(R.drawable.ic_blue_logo),
        null,
        modifier = Modifier
            .padding(start = dimens.normalPadding)
            .constrainAs(icon) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            })

    Image(
        painterResource(R.drawable.ic_wsr_connect),
        null,
        modifier = Modifier
            .padding(dimens.normalPadding)
            .constrainAs(text) {
                top.linkTo(icon.bottom)
                start.linkTo(icon.start)
            }
    )

    LaunchedEffect(true){
        delay(1000)
        navController.navigate(WSRConnectScreen.SignInScreen.destination)
    }
}

@Preview
@Composable
private fun LaunchScreenPreview() = WSRConnectTheme {
    LaunchScreen(rememberNavController())
}

