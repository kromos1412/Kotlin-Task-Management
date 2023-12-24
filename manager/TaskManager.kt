package manager

import model.Task

class TaskManager {
    private val tasks = mutableListOf<Task>()
    fun getTasks(): List<Task> {
        return tasks.toList()
    }

    fun addTask(task: Task) {
        tasks.add(task)
        println("Task added: ${task.name}")
    }

    fun displayTasks() {
        if (tasks.isEmpty()) {
            println("No tasks to display.")
        } else {
            println("Tasks:")
            tasks.forEachIndexed { index, task ->
                println("${index + 1}. $task")
            }
        }
    }

        fun sortTasksByDueDate() {
            tasks.sortBy { it.dueDate }
        }

        fun sortTasksByPriority() {
            tasks.sortByDescending { it.priority }
        }


        fun exitProgram() {
            println("Exiting Task Management App. Goodbye!")
        }
}