package util

import manager.TaskManager
import java.util.*

enum class SearchType {
    NAME, DEADLINE, PRIORITY
}

class TaskSearchUtil(private val taskManager: TaskManager) {

    fun searchTasks(keyword: String, searchType: SearchType) {
        val tasks = taskManager.getTasks()

        val results = when (searchType) {
            SearchType.NAME -> tasks.filter { it.name.contains(keyword, ignoreCase = true) }
            SearchType.DEADLINE -> tasks.filter { it.dueDate?.contains(keyword, ignoreCase = true) ?: false }
            SearchType.PRIORITY -> tasks.filter { it.priority?.toString()?.contains(keyword, ignoreCase = true) ?: false }
        }

        if (results.isEmpty()) {
            println("No tasks found with the keyword '$keyword' in the specified category.")
        } else {
            println("Search Results:")
            results.forEachIndexed { index, task ->
                println("${index + 1}. $task")
            }
        }
    }

    fun performTaskSearch(scanner: Scanner): Pair<String, SearchType> {
        val searchCategory: SearchType
        do {
            println("Search by:")
            println("1. Name")
            println("2. Deadline")
            println("3. Priority")
            print("Enter your search category (1-3): ")

            val userInput = scanner.next()

            searchCategory = when (userInput.toIntOrNull()) {
                1 -> SearchType.NAME
                2 -> SearchType.DEADLINE
                3 -> SearchType.PRIORITY
                else -> {
                    println("Invalid input. Please enter a number between 1 and 3.")
                    continue
                }
            }
            break  // Keluar dari loop jika input valid
        } while (true)

        var keyword: String
        do {
            print("Enter keyword to search for tasks: ")
            keyword = scanner.next()
            if (keyword.isBlank()) {
                println("Invalid input. Keyword cannot be empty.")
            } else {
                break  // Keluar dari loop jika input valid
            }
        } while (true)

        return Pair(keyword, searchCategory)
    }
}