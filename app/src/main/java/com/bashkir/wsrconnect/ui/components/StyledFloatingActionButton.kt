package com.bashkir.wsrconnect.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color

@Composable
fun StyledFloatingActionButton(onClick: () -> Unit = {}) = FloatingActionButton(onClick = onClick, backgroundColor = Color(0xFF0D47A1)) {
    Icon(Icons.Default.Create, null)
}