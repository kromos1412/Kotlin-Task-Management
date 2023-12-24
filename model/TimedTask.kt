package model

class TimedTask(name: String, dueDate: String?, priority: Int?, var estimatedTime: Int) : Task(name, dueDate, priority) {
    override fun toString(): String {
        return super.toString() + ", Estimated Time: $estimatedTime hours"
    }
}