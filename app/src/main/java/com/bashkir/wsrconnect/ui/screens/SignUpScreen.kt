package com.bashkir.wsrconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksActivityViewModel
import com.bashkir.wsrconnect.ConnectViewModel
import com.bashkir.wsrconnect.R
import com.bashkir.wsrconnect.ui.components.Background
import com.bashkir.wsrconnect.ui.components.ErrorDialog
import com.bashkir.wsrconnect.ui.components.StyledButton
import com.bashkir.wsrconnect.ui.components.StyledTextField
import com.bashkir.wsrconnect.ui.navigation.WSRConnectScreen
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme.dimens
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun SignUpScreen(navController: NavController, viewModel: ConnectViewModel) = ConstraintLayout {

    val nameField = remember { mutableStateOf(TextFieldValue()) }
    val emailField = remember { mutableStateOf(TextFieldValue()) }
    val passwordField = remember { mutableStateOf(TextFieldValue()) }
    val confirmPasswordField = remember { mutableStateOf(TextFieldValue()) }

    val errorDialog = rememberMaterialDialogState()
    val user by viewModel.collectAsState { it.user }

    SignUpView(
        nameField,
        emailField,
        passwordField,
        confirmPasswordField,
        onSignUp = {
            viewModel.signUp(
                nameField.value.text,
                emailField.value.text,
                passwordField.value.text,
                confirmPasswordField.value.text
            )
        },
        onCancel = { navController.popBackStack() }
    )

    LaunchedEffect(user) {
        when (user) {
            is Fail -> errorDialog.show()
            is Success -> navController.navigate(WSRConnectScreen.MainScreen.destination)
            else -> {}
        }
    }

    ErrorDialog(dialogState = errorDialog, async = user)
}

@Composable
private fun ConstraintLayoutScope.SignUpView(
    nameField: MutableState<TextFieldValue>,
    emailField: MutableState<TextFieldValue>,
    passwordField: MutableState<TextFieldValue>,
    confirmPasswordField: MutableState<TextFieldValue>,
    onSignUp: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    val (fields, buttons) = createRefs()

    Background(drawableId = R.drawable.ic_background)

    Image(
        painterResource(id = R.drawable.ic_colored_logo),
        null,
        Modifier.padding(dimens.bigPadding)
    )

    Fields(
        Modifier
            .fillMaxWidth()
            .padding(start = dimens.bigPadding)
            .padding(vertical = dimens.bigPadding)
            .constrainAs(fields) {
                bottom.linkTo(buttons.top)
            }, nameField, emailField, passwordField, confirmPasswordField
    )

    Buttons(
        Modifier
            .padding(dimens.bigPadding)
            .constrainAs(buttons) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }, onSignUp, onCancel
    )

}

@Composable
private fun Fields(
    modifier: Modifier = Modifier,
    nameField: MutableState<TextFieldValue>,
    emailField: MutableState<TextFieldValue>,
    passwordField: MutableState<TextFieldValue>,
    confirmPasswordField: MutableState<TextFieldValue>
) = Column(
    modifier, horizontalAlignment = Alignment.Start
) {
    StyledTextField(fieldValue = nameField, label = "Name")
    Spacer(Modifier.height(dimens.normalPadding))
    StyledTextField(fieldValue = emailField, label = "Email", keyboardType = KeyboardType.Email)
    Spacer(Modifier.height(dimens.normalPadding))
    StyledTextField(
        fieldValue = passwordField,
        label = "Password",
        keyboardType = KeyboardType.Password
    )
    Spacer(Modifier.height(dimens.normalPadding))
    StyledTextField(
        fieldValue = confirmPasswordField,
        label = "Confirm Password",
        keyboardType = KeyboardType.Password
    )
}

@Composable
private fun Buttons(modifier: Modifier = Modifier, onSignUp: () -> Unit, onCancel: () -> Unit) =
    Column(
        modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StyledButton(text = "Sign Up", onClick = onSignUp)
        Spacer(Modifier.height(dimens.normalPadding))
        StyledButton(text = "Cancel", onClick = onCancel)
    }

@Preview
@Composable
private fun SignUpScreenPreview() = WSRConnectTheme {
    SignUpScreen(navController = rememberNavController(), mavericksActivityViewModel())
}