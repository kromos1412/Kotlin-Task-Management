package util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

object InputUtil {
    private val scanner = Scanner(System.`in`)
    private val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun readStringInput(prompt: String, scanner: Scanner): String {
        print(prompt)
        return scanner.nextLine().trim()
    }

    fun readDateInput(prompt: String, scanner: Scanner): String? {
        do {
            print(prompt)
            val dateInput = scanner.nextLine().takeIf { it.isNotBlank() }

            if (dateInput.isNullOrBlank()) {
                return null
            } else {
                try {
                    LocalDate.parse(dateInput, dateFormat)
                    return dateInput
                } catch (e: DateTimeParseException) {
                    println("Error: Invalid date format. Please enter a date in the format YYYY-MM-DD.")
                }
            }
        } while (true)
    }

    fun readIntInput(prompt: String, errorMessage: String): Int {
        do {
            print(prompt)
            try {
                return scanner.nextInt().also {
                    if (it !in 1..3) {
                        throw InputMismatchException(errorMessage)
                    }
                }
            } catch (e: InputMismatchException) {
                println("Error: $errorMessage")
                scanner.nextLine()
            }
        } while (true)
    }

    fun readBooleanInput(prompt: String): Boolean {
        do {
            print(prompt)
            try {
                val userInput = scanner.nextInt()

                if (userInput == 1 || userInput == 0) {
                    return userInput == 1
                } else {
                    println("Error: Invalid input. Please enter either 0 or 1.")
                }
            } catch (e: InputMismatchException) {
                println("Error: Invalid input. Please enter either 0 or 1.")
                scanner.nextLine()
            }
        } while (true)
    }

    fun readEstimatedTime(isTimedTask: Boolean): Int? {
        if (!isTimedTask) {
            return null
        }

        do {
            print("Enter estimated time (in hours, required, up to 24 hours): ")
            try {
                val inputTime = scanner.nextInt()

                if (inputTime in 1..24) {
                    return inputTime
                } else {
                    println("Error: Invalid estimated time. Please enter a value between 1 and 24.")
                }
            } catch (e: InputMismatchException) {
                println("Error: Invalid input. Please enter a valid number.")
                scanner.nextLine()
            }
        } while (true)
    }
}