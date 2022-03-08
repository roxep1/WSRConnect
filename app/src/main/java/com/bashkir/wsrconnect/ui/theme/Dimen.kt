package com.bashkir.wsrconnect.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Dimens(
    val normalPadding: Dp,
    val bigPadding: Dp,
    val smallText: TextUnit,
    val normalText: TextUnit,
    val titleText: TextUnit,
    val buttonText: TextUnit,
    val defaultStroke: Dp,
    val elevation: Dp
)

val standardDimens = Dimens(
    normalPadding = 10.dp,
    bigPadding = 20.dp,
    smallText = 12.sp,
    normalText = 14.sp,
    titleText = 16.sp,
    buttonText = 24.sp,
    defaultStroke = 1.dp,
    elevation = 6.dp
)