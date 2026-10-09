package com.example.appmpazpro2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appmpazpro2.ui.HomeScreen
import com.example.appmpazpro2.ui.theme.AppMPAZPRO2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppMPAZPRO2Theme {
                HomeScreen()
            }
        }
    }
}
