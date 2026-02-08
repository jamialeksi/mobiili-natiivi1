package com.example.viikko1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    onGoHome: () -> Unit,
    onGoCalendar: () -> Unit
) {
    var dark by remember { mutableStateOf(false) } // dummy, ei pakko tallentaa

    Column(Modifier.padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = onGoHome) { Text("Home") }
            Button(onClick = onGoCalendar) { Text("Calendar") }
        }

        Spacer(Modifier.height(16.dp))

        Text("Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Dark mode (dummy)")
            Switch(checked = dark, onCheckedChange = { dark = it })
        }
    }
}
