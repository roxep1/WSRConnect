package com.bashkir.wsrconnect.ui.components

import androidx.compose.runtime.Composable
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Fail
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.message
import com.vanpra.composematerialdialogs.title

@Composable
fun ErrorDialog(dialogState: MaterialDialogState, async: Async<*>) =
    MaterialDialog(dialogState = dialogState, buttons = { positiveButton("OK") }) {
        title("Произошла ошибка")
        message(if (async is Fail) async.error.localizedMessage else "No error message")
    }