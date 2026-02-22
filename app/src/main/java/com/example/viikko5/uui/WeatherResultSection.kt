package com.example.viikko5.uui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun WeatherResultSection(
    city: String,
    temp: Double,
    description: String
) {
    Text(text = "City: $city")
    Text(text = "Temp: $temp °C")
    Text(text = "Description: $description")
}