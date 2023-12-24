package model

open class Task(
    var name: String,
    var dueDate: String?,
    var priority: Int?
) {
    override fun toString(): String {
        val dueDateText = dueDate ?: "Not specified"
        val priorityText = priority ?: "Not specified"
        return "$name - Deadline: $dueDateText, Priority: $priorityText"
    }
}