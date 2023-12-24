package util

import model.Task
import java.util.*

class TaskCompleter(private val tasks: List<Task>) {
    fun completeTask() {
        displayTasks()

        if (tasks.isEmpty()) {
            println("No tasks to complete.")
            return
        }

        val scanner = Scanner(System.`in`)
        print("Enter the index of the task to mark as completed: ")

        try {
            val index = scanner.nextInt()

            if (index in 1..tasks.size) {
                val completedTask = tasks.toMutableList().removeAt(index - 1)
                println("Task completed: ${completedTask.name}")
            } else {
                println("Invalid index. Please enter a valid index.")
            }
        } catch (e: InputMismatchException) {
            println("Error: Invalid input. Please enter a valid number.")
        } catch (e: ClassCastException) {
            println("Error: Unable to cast the list to MutableList. The task list may be immutable.")
        }
    }

    private fun displayTasks() {
        if (tasks.isEmpty()) {
            println("No tasks to display.")
        } else {
            println("Tasks:")
            tasks.forEachIndexed { index, task ->
                println("${index + 1}. $task")
            }
        }
    }
}