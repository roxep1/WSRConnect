package com.bashkir.wsrconnect.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun Background(@DrawableRes drawableId: Int) =
    Image(
        painter = painterResource(drawableId),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
    )