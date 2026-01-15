package com.example.viikko1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.mockTasks
import com.example.viikko1.domain.filterByDone
import com.example.viikko1.domain.sortByDueDate

@Composable
fun HomeScreen() {

    var currentList by remember { mutableStateOf(mockTasks) }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Tehtävälista",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Button(onClick = {
                currentList = mockTasks
            }) {
                Text("Kaikki")
            }

            Button(onClick = {
                currentList = filterByDone(mockTasks, true)
            }) {
                Text("Valmiit")
            }

            Button(onClick = {
                currentList = filterByDone(mockTasks, false)
            }) {
                Text("Kesken")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                currentList = sortByDueDate(currentList)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Järjestä päivämäärän mukaan")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // --- LISTA ---
        currentList.forEach { task ->
            TaskRow(task = task)
        }
    }
}

@Composable
fun TaskRow(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            Text(text = task.description, style = MaterialTheme.typography.bodySmall)
        }
        Text(
            text = if (task.done) "Valmis" else "Kesken",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
