package dev.dicesystems.varsitutor.data.models

data class LoginModel(
    val email: String,
    val password: String,
    val device_name: String
)
