import manager.TaskManager
import manager.UserManager
import model.Task
import user.User
import util.*
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    val taskManager = TaskManager()
    val userManager = UserManager()
    val loggedInUser: User? = userManager.loginUser()

    // Informasi User
    loggedInUser?.let { user ->
        println("===== User Info =====\n${user.getUserInfo()}\n")
        val scanner = Scanner(System.`in`)
        DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var choice: Int

        do {
            // Pilihan Menu Task Management
            displayMenu()

            try {
                choice = scanner.nextInt()
                scanner.nextLine()

                when (choice) {
                    1 -> {
                        // Add Task
                        TaskUtil.addTask(taskManager, scanner)
                    }

                    2 -> {
                        // Displaying tasks
                        displaySortedTasks(taskManager)
                    }

                    3 -> {
                        // Complete Task
                        val taskCompleter = TaskCompleter(taskManager.getTasks() as MutableList<Task>)
                        taskCompleter.completeTask()
                    }

                    4 -> {
                        // Modifying a task
                        val taskModifier = TaskModifier(scanner)
                        taskModifier.modifyTask(taskManager)
                    }

                    5 -> {
                        // Searching for tasks
                        val taskSearchUtil = TaskSearchUtil(taskManager)
                        val (keyword, searchType) = taskSearchUtil.performTaskSearch(scanner)
                        taskSearchUtil.searchTasks(keyword, searchType)
                    }

                    6 -> {
                        // Exit Program
                        taskManager.exitProgram()
                    }

                    else -> {
                        println("Invalid choice. Please enter a valid option.")
                    }
                }
            } catch (e: InputMismatchException) {
                println("Error: Invalid input. Please enter a valid number.")
                scanner.nextLine()
                choice = 0
            } catch (e: Exception) {
                println("An unexpected error occurred: ${e.message}")
                choice = 0
            }

        } while (choice != 6)
    }
}