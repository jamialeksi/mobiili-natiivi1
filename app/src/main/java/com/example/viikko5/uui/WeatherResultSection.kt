package com.example.viikko5.uui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.viikko5.data.model.WeatherResponse

@Composable
fun WeatherResultSection(weather: WeatherResponse) {
    Text(text = "Temp: ${weather.main.temp} °C")
    Text(text = "onsite: ${weather.weather.firstOrNull()?.description ?: "-"}")
}
