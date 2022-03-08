package com.bashkir.wsrconnect.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun SignInScreen(navController: NavController, viewModel: ConnectViewModel) = ConstraintLayout {

    val emailField = remember { mutableStateOf(TextFieldValue()) }
    val passwordField = remember { mutableStateOf(TextFieldValue()) }

    val errorDialog = rememberMaterialDialogState()
    val user by viewModel.collectAsState { it.user }

    SignInView(emailField = emailField, passwordField = passwordField, onSignUp = {
        navController.navigate(WSRConnectScreen.SignUpScreen.destination)
    }, onSignIn = {
        viewModel.signIn(emailField.value.text, passwordField.value.text)
    })

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
private fun ConstraintLayoutScope.SignInView(
    emailField: MutableState<TextFieldValue>,
    passwordField: MutableState<TextFieldValue>,
    onSignIn: () -> Unit = {},
    onSignUp: () -> Unit = {}
) {
    val (email, password, google, buttons) = createRefs()

    Background(R.drawable.ic_background)

    Image(
        painterResource(R.drawable.ic_colored_logo),
        null,
        modifier = Modifier.padding(dimens.normalPadding)
    )

    StyledTextField(
        fieldValue = emailField, label = "Email",
        modifier = Modifier
            .constrainAs(email) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .padding(dimens.bigPadding),
        keyboardType = KeyboardType.Email
    )

    StyledTextField(fieldValue = passwordField, label = "Password",
        modifier = Modifier
            .constrainAs(password) {
                top.linkTo(email.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, keyboardType = KeyboardType.Password
    )

    Image(
        painterResource(id = R.drawable.ic_google_logo),
        null,
        modifier = Modifier
            .constrainAs(google) {
                top.linkTo(password.bottom)
                start.linkTo(password.start)
            }
            .padding(top = dimens.normalPadding)
    )

    Column(
        Modifier
            .constrainAs(buttons) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
            .padding(dimens.bigPadding), horizontalAlignment = Alignment.CenterHorizontally) {
        StyledButton(text = "Sign In", onClick = onSignIn)
        Spacer(Modifier.height(dimens.normalPadding))
        StyledButton(text = "Sign Up", onClick = onSignUp)
    }
}

@Preview
@Composable
private fun SignInScreenPreview() = WSRConnectTheme {
    SignInScreen(navController = rememberNavController(), mavericksActivityViewModel())
}