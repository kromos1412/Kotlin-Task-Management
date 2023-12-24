package util

import manager.TaskManager
import model.Task
import model.TimedTask
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

class TaskModifier(private val scanner: Scanner) {
    fun modifyTask(taskManager: TaskManager) {
        taskManager.displayTasks()
        print("Enter the index of the task to modify: ")
        val modifyIndex = scanner.nextInt()
        scanner.nextLine() // Membersihkan karakter newline di buffer

        if (modifyIndex > 0 && modifyIndex <= taskManager.getTasks().size) {
            val taskToModify = taskManager.getTasks()[modifyIndex - 1]
            modifyTaskAttributes(taskToModify)
        } else {
            println("Invalid index. Please enter a valid index.")
        }
    }

    private fun modifyTaskAttributes(taskToModify: Task) {
        println("Modify Options:")
        println("1. Name")
        println("2. Due Date")
        println("3. Priority")
        println("4. Estimated Time (for TimedTask)")
        print("Enter your modify choice: ")

        val modifyChoice = scanner.nextInt()
        scanner.nextLine()

        when (modifyChoice) {
            1 -> modifyTaskName(taskToModify)
            2 -> modifyDueDate(taskToModify)
            3 -> modifyPriority(taskToModify)
            4 -> modifyEstimatedTime(taskToModify)
            else -> println("Invalid choice. Please enter a valid choice.")
        }
    }

    private fun modifyTaskName(taskToModify: Task) {
        var newName: String
        do {
            print("Enter new task name: ")
            newName = scanner.nextLine().trim()

            try {
                if (newName.isBlank()) {
                    throw IllegalArgumentException("Task name cannot be empty.")
                }

                taskToModify.name = newName
                println("Task name modified successfully.")
            } catch (e: IllegalArgumentException) {
                println("Error: ${e.message}")
            }
        } while (newName.isBlank())
    }

    private fun modifyDueDate(taskToModify: Task) {
        var newDueDate: String?
        do {
            print("Enter new due date (Format: YYYY-MM-DD): ")
            newDueDate = scanner.nextLine()

            if (newDueDate.isNullOrBlank()) {
                println("Error: Due date cannot be empty.")
            } else {
                try {
                    LocalDate.parse(newDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    taskToModify.dueDate = newDueDate
                    println("Due date modified successfully.")
                } catch (e: DateTimeParseException) {
                    println("Error: Invalid date format. Please enter a date in the format YYYY-MM-DD.")
                    newDueDate = null
                }
            }
        } while (newDueDate == null)
    }

    private fun modifyPriority(taskToModify: Task) {
        var newPriority: Int
        do {
            try {
                print("Enter new priority (1 = Low, 2 = Medium, 3 = High): ")
                newPriority = scanner.nextInt()

                if (newPriority !in 1..3) {
                    throw IllegalArgumentException("Invalid priority. Please enter a priority between 1 and 3.")
                }

                taskToModify.priority = newPriority
                println("Priority modified successfully.")
            } catch (e: InputMismatchException) {
                println("Error: Invalid input. Please enter a valid number.")
                scanner.nextLine() // Membersihkan buffer
                newPriority = 0
            } catch (e: IllegalArgumentException) {
                println("Error: ${e.message}")
                newPriority = 0
            }
        } while (newPriority == 0)
    }

    private fun modifyEstimatedTime(taskToModify: Task) {
        val isTimedTask: Boolean = taskToModify is TimedTask
        var newEstimatedTime: Int?

        if (!isTimedTask) {
            println("This Task isn't a Timed Task\n")
        } else {
            do {
                try {
                    print("Enter new estimated time (in hours, up to 24 hours): ")
                    newEstimatedTime = scanner.nextInt()

                    if (newEstimatedTime in 1..24) {
                        (taskToModify as? TimedTask)?.estimatedTime = newEstimatedTime
                        println("Estimated time modified successfully.")
                    } else {
                        println("Error: Invalid estimated time. Please enter a value between 1 and 24.")
                    }
                } catch (e: InputMismatchException) {
                    println("Error: Invalid input. Please enter a valid number.")
                    scanner.nextLine() // Clear the buffer
                    newEstimatedTime = null
                }

            } while (newEstimatedTime == null)
        }
    }
}
