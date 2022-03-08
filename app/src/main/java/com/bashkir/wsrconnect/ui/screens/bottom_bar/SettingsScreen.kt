package com.bashkir.wsrconnect.ui.screens.bottom_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import com.bashkir.wsrconnect.ui.components.StyledTopBar
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme.dimens
import com.bashkir.wsrconnect.ui.theme.backGroundColor
import com.bashkir.wsrconnect.ui.theme.darkBlueColor
import com.bashkir.wsrconnect.ui.theme.lightBlueColor

@Composable
fun SettingsScreen() =
    Scaffold(topBar = { StyledTopBar(title = "Settings") }, backgroundColor = backGroundColor) {
        Box(
            Modifier
                .fillMaxWidth()
                .shadow(dimens.elevation)
                .background(Color.White)
        ) {
            var checkedState by remember { mutableStateOf(true) }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.normalPadding)
                    .padding(vertical = dimens.bigPadding)
                    .align(Alignment.Center),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Notifications", color = Color.Black)
                Switch(
                    checked = checkedState,
                    onCheckedChange = { checkedState = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = darkBlueColor,
                        uncheckedThumbColor = darkBlueColor,
                        checkedTrackColor = lightBlueColor,
                        uncheckedTrackColor = Color.Gray
                    )
                )
            }
        }
    }