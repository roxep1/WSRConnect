package com.bashkir.wsrconnect.ui.screens.bottom_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bashkir.wsrconnect.R
import com.bashkir.wsrconnect.ui.components.StyledFloatingActionButton
import com.bashkir.wsrconnect.ui.components.StyledTopBar
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme.dimens
import com.bashkir.wsrconnect.ui.theme.backGroundColor
import com.bashkir.wsrconnect.ui.theme.searchShape

@Composable
fun ChatScreen(navController: NavController) = Scaffold(
    topBar = { StyledTopBar("Chats") },
    floatingActionButton = { StyledFloatingActionButton() },
    backgroundColor = backGroundColor
) {
    var searchField by remember { mutableStateOf(TextFieldValue()) }

    TextField(
        modifier = Modifier
            .padding(dimens.normalPadding)
            .fillMaxWidth()
            .shadow(elevation = dimens.elevation, shape = searchShape),
        value = searchField,
        onValueChange = { searchField = it },
        trailingIcon = { Icon(Icons.Default.Search, null) },
        shape = searchShape,
        placeholder = { Text("Search for messages or users", color = Color.Gray) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            trailingIconColor = Color.Black,
            cursorColor = Color.Black,
            textColor = Color.Black
        )
    )

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(R.drawable.ic_no_messages), null)
        Spacer(Modifier.height(dimens.normalPadding))
        Text(
            "So lonely and sad...\n" +
                    "But lets light up\n" +
                    "this place a bit,\n" +
                    "start a new chat\n" +
                    "with your friend!",
            color = Color.Black,
            fontSize = dimens.titleText,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}