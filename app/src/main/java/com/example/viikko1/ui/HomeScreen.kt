package com.example.viikko1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.viewmodel.TaskViewModel
//import java.time.LocalDate jotakin erroria antaa kun yritti puskea kun tätä käytti päivämääränä. EI SITTE

@Composable
fun HomeScreen() {

    val vm: TaskViewModel = viewModel()
    val currentList = vm.tasks

    var title by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {

        Text(
            text = "Tehtävälista",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(onClick = { vm.showAll() }) {
                Text("Kaikki")
            }

            Button(onClick = { vm.filterByDone(true) }) { Text("Valmiit") }

            Button(onClick = { vm.filterByDone(false) }) { Text("Kesken") }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                vm.sortByDueDate()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Järjestä päivämäärän mukaan")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(Modifier.fillMaxWidth()) {
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                val newId = (vm.tasks.maxOfOrNull { it.id } ?: 0) + 1
                vm.addTask(
                    Task(
                        id = newId,
                        title = title,
                        description = "",
                        priority = 1,
                        dueDate = "2026-01-01", //täällä oli localdate käytössä
                        done = false
                    )
                )
                title = ""
            }) {
                Text("Lisää")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(currentList) { task ->
                TaskRow(
                    task = task,
                    onToggle = { vm.toggleDone(task.id) },
                    onDelete = { vm.removeTask(task.id) }
                )
            }
        }
    }
}

@Composable
fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = task.done,
            onCheckedChange = { onToggle() }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 6.dp)
        ) {
            Text(text = task.title)
            Text(
                text = task.dueDate,
                style = MaterialTheme.typography.bodySmall
            )
        }


        Button(onClick = { onDelete() }) {
            Text("Poista")
        }
    }
}
