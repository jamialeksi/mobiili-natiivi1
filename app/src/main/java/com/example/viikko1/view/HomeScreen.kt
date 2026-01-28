package com.example.viikko1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.model.Task
import com.example.viikko1.viewmodel.TaskViewModel

@Composable
fun HomeScreen(vm: TaskViewModel = viewModel()) {

    val tasks by vm.tasks.collectAsState()

    var selected by remember { mutableStateOf<Task?>(null) }
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {

        Text("Task List", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(title, { title = it }, label = { Text("Title") })
        OutlinedTextField(desc, { desc = it }, label = { Text("Description") })

        Button(onClick = {
            vm.addTask(title, desc)
            title = ""
            desc = ""
        }) {
            Text("Add")
        }

        LazyColumn {
            items(tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { selected = task }
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(task.title)
                        Text(task.description)
                    }
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = { vm.toggleDone(task.id) }
                    )
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
            onDelete = {
                vm.removeTask(it)
                selected = null
            }
        )
    }
}

@Composable
fun EditTaskDialog(
    task: Task,
    onCancel: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: (Int) -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var desc by remember { mutableStateOf(task.description) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Edit Task") },
        text = {
            Column {
                OutlinedTextField(title, { title = it })
                OutlinedTextField(desc, { desc = it })
            }
        },
        confirmButton = {
            Button(onClick = { onSave(task.copy(title = title, description = desc)) }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDelete(task.id) }) { Text("Delete") }
        }
    )
}
