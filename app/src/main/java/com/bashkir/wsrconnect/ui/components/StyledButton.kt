package com.bashkir.wsrconnect.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme
import com.bashkir.wsrconnect.ui.theme.controlShape
import com.bashkir.wsrconnect.ui.theme.darkBlueColor

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    backGroundColor: Color = darkBlueColor,
    textColor: Color = Color.White,
    borderColor: Color = Color.White
) = OutlinedButton(
    modifier = modifier, onClick = onClick,
    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = backGroundColor),
    shape = controlShape,
    border = BorderStroke(WSRConnectTheme.dimens.defaultStroke, borderColor)
) {
    Text(text, fontSize = WSRConnectTheme.dimens.buttonText, color = textColor)
}