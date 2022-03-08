package com.bashkir.wsrconnect.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.bashkir.wsrconnect.ui.theme.controlShape
import com.bashkir.wsrconnect.ui.theme.labelText
import com.bashkir.wsrconnect.ui.theme.lightBlueColor

@Composable
fun StyledTextField(
    modifier: Modifier = Modifier,
    fieldValue: MutableState<TextFieldValue>,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) = OutlinedTextField(
    fieldValue.value,
    onValueChange = { fieldValue.value = it },
    label = { Text(label, style = labelText) },
    modifier = modifier,
    shape = controlShape,
    colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = lightBlueColor,
        unfocusedBorderColor = lightBlueColor
    ),
    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
)