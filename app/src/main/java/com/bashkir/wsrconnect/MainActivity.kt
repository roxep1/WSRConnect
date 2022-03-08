package com.bashkir.wsrconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.mvrx.compose.mavericksActivityViewModel
import com.bashkir.wsrconnect.ui.navigation.CreateMainNavHost
import com.bashkir.wsrconnect.ui.theme.WSRConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WSRConnectTheme {
                Surface(color = MaterialTheme.colors.background) {
                    StartMainActivity()
                }
            }
        }
    }

    @Composable
    private fun StartMainActivity() =
        CreateMainNavHost(rememberNavController(), mavericksActivityViewModel())
}


