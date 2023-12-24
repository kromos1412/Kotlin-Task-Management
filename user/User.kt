package user

import manager.TaskManager

data class User(
    val username: String,
    val email: String,
    private val taskManager: TaskManager,
    var isLoggedIn: Boolean = false,
    var lastLogin: String? = null
) {

    // Metode untuk mendapatkan informasi pengguna
    fun getUserInfo(): String {
        return "Username: $username\nEmail: $email\nLast Login: $lastLogin"
    }
}