// TaskUtil.kt
package util

import manager.TaskManager
import model.Task
import model.TimedTask
import java.util.*

object TaskUtil {
    fun addTask(taskManager: TaskManager, scanner: Scanner) {
        val taskName = InputUtil.readStringInput("Enter task name: ", scanner)
        val taskDate = InputUtil.readDateInput("Enter task date (YYYY-MM-DD): ", scanner)
        val taskPriority = InputUtil.readIntInput("Enter task priority (1-3): ", "Priority must be between 1 and 3.")
        val isTimedTask = InputUtil.readBooleanInput("Is it a timed task? (1 for yes, 0 for no): ")

        // Use readEstimatedTime only if it is a timed task
        val estimatedTime = if (isTimedTask) {
            InputUtil.readEstimatedTime(true)
        } else {
            null
        }

        val newTask = if (isTimedTask && estimatedTime != null) {
            TimedTask(taskName, taskDate, taskPriority, estimatedTime)
        } else {
            Task(taskName, taskDate, taskPriority)
        }

        taskManager.addTask(newTask)
        println("Task added successfully.")
    }
}
