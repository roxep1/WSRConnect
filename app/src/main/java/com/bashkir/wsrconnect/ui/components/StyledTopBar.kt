package com.bashkir.wsrconnect.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.bashkir.wsrconnect.ui.theme.darkBlueColor

@Composable
fun StyledTopBar(title: String) = TopAppBar(
    title = {Text(title, Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.White)},
    backgroundColor = darkBlueColor
)