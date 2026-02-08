package com.example.viikko1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel

@Composable
fun CalendarScreen(
    vm: TaskViewModel,
    onGoHome: () -> Unit,
    onGoSettings: () -> Unit
) {
    val tasks by vm.tasks.collectAsState()
    var selected by remember { mutableStateOf<Task?>(null) }

    val grouped = remember(tasks) { tasks.groupBy { it.dueDate } }

    Column(Modifier.padding(16.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = onGoHome) { Text("List") }
            Button(onClick = onGoSettings) { Text("Settings") }
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn {
            grouped.keys.sorted().forEach { date ->
                item {
                    Text(date, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(vertical = 8.dp))
                }
                items(grouped[date] ?: emptyList()) { task ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { selected = task }
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(task.title)
                            Text(task.description, style = MaterialTheme.typography.bodySmall)
                        }
                        Checkbox(
                            checked = task.done,
                            onCheckedChange = { vm.toggleDone(task.id) }
                        )
                    }
                }
            }
        }
    }

    selected?.let { task ->
        EditTaskDialog(
            task = task,
            onCancel = { selected = null },
            onSave = {
                vm.updateTask(it)
                selected = null
            },
            onDelete = { id ->
                vm.removeTask(id)
                selected = null
            }
        )
    }
}
