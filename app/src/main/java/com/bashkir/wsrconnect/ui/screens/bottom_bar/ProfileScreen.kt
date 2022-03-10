package com.bashkir.wsrconnect.ui.screens.bottom_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.bashkir.wsrconnect.ConnectViewModel
import com.bashkir.wsrconnect.R
import com.bashkir.wsrconnect.ui.components.StyledButton
import com.bashkir.wsrconnect.ui.components.StyledTopBar
import com.bashkir.wsrconnect.ui.navigation.WSRConnectScreen
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme.dimens
import com.bashkir.wsrconnect.ui.theme.backGroundColor
import com.bashkir.wsrconnect.ui.theme.darkBlueColor
import com.bashkir.wsrconnect.ui.theme.labelText
import com.google.firebase.auth.UserProfileChangeRequest

@Composable
fun ProfileScreen(viewModel: ConnectViewModel, mainNavController: NavController) =
    Scaffold(topBar = { StyledTopBar(title = "Profile") }, backgroundColor = backGroundColor) {

        Column(Modifier.fillMaxSize()) {

            Head(
                Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                viewModel
            )
            Fields(
                Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
                    .padding(dimens.normalPadding),
                viewModel
            )

            BoxWithExitButton(
                Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
            ) {
                viewModel.signOut()
                mainNavController.navigate(WSRConnectScreen.SignInScreen.destination)
            }

        }
    }

@Composable
private fun Head(modifier: Modifier, viewModel: ConnectViewModel) = Box(modifier) {
    val user by viewModel.collectAsState{it.user}

    Image(
        painterResource(R.drawable.example_image),
        null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(
        Modifier
            .padding(dimens.normalPadding)
            .align(Alignment.BottomStart)
    ) {
        Text(
            user()?.displayName?:"",
            color = Color.White,
            fontSize = dimens.buttonText,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimens.normalPadding))
        Text(user()?.email?:"", color = Color.White, fontSize = dimens.titleText)
    }
}

@Composable
private fun Fields(
    modifier: Modifier = Modifier,
    viewModel: ConnectViewModel
) = Column(
    modifier,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val user by viewModel.collectAsState{it.user}

    val nameField = remember { mutableStateOf(TextFieldValue(user()?.displayName?:"")) }
    val emailField = remember { mutableStateOf(TextFieldValue(user()?.email?:"")) }
    val passwordField = remember { mutableStateOf(TextFieldValue()) }

    var isPasswordVisible by remember { mutableStateOf(false) }

    ProfileTextField(
        fieldValue = nameField,
        label = "Name",
        onCreateClick = { viewModel.updateName(nameField.value.text) })
    Spacer(Modifier.height(dimens.normalPadding))
    ProfileTextField(
        fieldValue = emailField,
        label = "Email",
        isEmail = true,
        onCreateClick = { viewModel.updateEmail(emailField.value.text) })
    Spacer(Modifier.height(dimens.normalPadding))
    ProfileTextField(
        fieldValue = passwordField,
        label = "Password",
        onCreateClick = { viewModel.updatePassword(passwordField.value.text) },
        isPassword = true,
        isVisible = isPasswordVisible,
        additionalTrailingIcon = {
            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painterResource(if (isPasswordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                    null
                )
            }
        })
}

@Composable
private fun BoxWithExitButton(modifier: Modifier = Modifier, onClick: () -> Unit) = Box(modifier) {
    StyledButton(
        Modifier.align(Alignment.Center),
        text = "Exit",
        onClick = onClick,
        backGroundColor = backGroundColor,
        borderColor = darkBlueColor,
        textColor = darkBlueColor
    )
}

@Composable
private fun ProfileTextField(
    fieldValue: MutableState<TextFieldValue>,
    label: String,
    additionalTrailingIcon: @Composable () -> Unit = {},
    isPassword: Boolean = false,
    isVisible: Boolean = true,
    isEmail: Boolean = false,
    onCreateClick: () -> Unit = {}
) = TextField(
    modifier = Modifier.fillMaxWidth(),
    value = fieldValue.value,
    onValueChange = { fieldValue.value = it },
    trailingIcon = {
        Row {
            additionalTrailingIcon()
            IconButton(onClick = onCreateClick) {
                Icon(
                    Icons.Default.Create, null
                )
            }
        }
    },
    label = { Text(label, style = labelText) },
    visualTransformation = if (!isVisible) PasswordVisualTransformation() else VisualTransformation.None,
    keyboardOptions = KeyboardOptions(
        keyboardType = when {
            isPassword -> KeyboardType.Password
            isEmail -> KeyboardType.Email
            else -> KeyboardType.Text
        }
    ),
    colors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        backgroundColor = backGroundColor,
        trailingIconColor = darkBlueColor,
        cursorColor = Color.Black,
        unfocusedIndicatorColor = Color.Black,
        focusedIndicatorColor = Color.Black
    )
)