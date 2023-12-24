package manager

import user.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserManager {
    private val users = mutableMapOf<String, User>()
    init {
        // Menambahkan user ke dalam map
        val user1 = User("achmad", "achmad@gmail.com", TaskManager())
        val user2 = User("farizi", "farizi@gmail.com", TaskManager())

        users[user1.username] = user1
        users[user1.email] = user1
        users[user2.username] = user2
        users[user2.email] = user2
    }

    fun loginUser(): User? {
        while (true) {
            print("Enter username or email to login (or 'exit' to quit): ")
            val inputIdentifier = readlnOrNull()

            if (inputIdentifier == "exit") {
                println("Exiting Task Management App. Goodbye!")
                return null
            }

            val selectedUser = users[inputIdentifier]

            if (selectedUser != null) {
                selectedUser.isLoggedIn = true
                selectedUser.lastLogin = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                return selectedUser
            } else {
                println("Invalid username or email. Please enter a valid username or email.")
            }
        }
    }
}
