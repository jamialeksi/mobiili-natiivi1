package com.example.viikko5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.viikko5.uui.WeatherScreen
import com.example.viikko5.ui.theme.Viikko5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Viikko5Theme {
                WeatherScreen()
            }
        }
    }
}
