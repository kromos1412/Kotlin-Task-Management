// displayTask.kt
package util

import manager.TaskManager
import java.util.*

fun displaySortedTasks(taskManager: TaskManager) {
    val tasks = taskManager.getTasks()

    if (tasks.isEmpty()) {
        println("No tasks to display.")
    } else {
        println("Tasks:")
        tasks.forEachIndexed { index, task ->
            println("${index + 1}. $task")
        }
    }

    val scanner = Scanner(System.`in`)

    print("Do you want to sort tasks? (1 = Yes, 0 = No): ")
    val sortChoice = scanner.nextInt()

    when (sortChoice) {
        1 -> {
            // Submenu pengurutan
            println("Sort Options:")
            println("1. By Due Date")
            println("2. By Priority")
            print("Enter your sort choice: ")

            val sortOption = scanner.nextInt()

            when (sortOption) {
                1 -> {
                    // Sort tasks by due date
                    taskManager.sortTasksByDueDate()
                    println("Tasks sorted by due date.")
                }

                2 -> {
                    // Sort tasks by priority
                    taskManager.sortTasksByPriority()
                    println("Tasks sorted by priority.")
                }

                else -> {
                    println("Invalid sort choice. Tasks remain unsorted.")
                }
            }
        }

        0 -> {
            println("Tasks remain unsorted.")
        }

        else -> {
            println("Invalid input. Tasks remain unsorted.")
        }
    }
}
