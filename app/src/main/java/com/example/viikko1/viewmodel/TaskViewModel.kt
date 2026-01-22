package com.example.viikko1.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.mockTasks

class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf(listOf<Task>())
        private set

    private var allTasks: List<Task> = emptyList()

    init {
        allTasks = mockTasks
        tasks = allTasks
    }

    fun showAll() {
        tasks = allTasks
    }

    fun filterByDone(done: Boolean) {
        tasks = allTasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }

    fun addTask(task: Task) {
        allTasks = allTasks + task
        tasks = allTasks
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { if (it.id == id) it.copy(done = !it.done) else it }
        tasks = allTasks
    }

    fun removeTask(id: Int) {
        allTasks = allTasks.filter { it.id != id }
        tasks = allTasks
    }
}
