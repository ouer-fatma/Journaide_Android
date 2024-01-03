package tn.esprit.journaide.models.modelResponse

data class LoginResponse(
    val message: String,
    val token: String,
    val usernameField: String,
    val email: String,
    val user: User,
)


data class User(
    val Username: String,
    val Email: String,
)
