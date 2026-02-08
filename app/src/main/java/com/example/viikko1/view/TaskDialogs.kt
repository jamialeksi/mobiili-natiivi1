package com.example.viikko1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko1.model.Task

@Composable
fun AddTaskDialog(
    onCancel: () -> Unit,
    onSave: (title: String, description: String, dueDate: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("2026-02-05") } // minimi: oletus

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Add Task") },
        text = {
            Column {
                OutlinedTextField(title, { title = it }, label = { Text("Title") })
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(desc, { desc = it }, label = { Text("Description") })
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(dueDate, { dueDate = it }, label = { Text("Due date (yyyy-MM-dd)") })
            }
        },
        confirmButton = {
            Button(onClick = { onSave(title, desc, dueDate) }) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onCancel) { Text("Cancel") }
        }
    )
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
    var dueDate by remember { mutableStateOf(task.dueDate) }

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Edit Task") },
        text = {
            Column {
                OutlinedTextField(title, { title = it }, label = { Text("Title") })
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(desc, { desc = it }, label = { Text("Description") })
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(dueDate, { dueDate = it }, label = { Text("Due date (yyyy-MM-dd)") })
            }
        },
        confirmButton = {
            Button(onClick = { onSave(task.copy(title = title, description = desc, dueDate = dueDate)) }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDelete(task.id) }) { Text("Delete") }
        }
    )
}
