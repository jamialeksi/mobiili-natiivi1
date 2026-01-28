package com.example.viikko1.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viikko1.model.Task
import com.example.viikko1.model.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow(mockTasks)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(title: String, description: String) {
        val newId = (_tasks.value.maxOfOrNull { it.id } ?: 0) + 1
        _tasks.value = _tasks.value + Task(newId, title, description, false)
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map { t ->
            if (t.id == id) t.copy(done = !t.done) else t
        }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filter { it.id != id }
    }

    fun updateTask(updated: Task) {
        _tasks.value = _tasks.value.map { t ->
            if (t.id == updated.id) updated else t
        }
    }
}
