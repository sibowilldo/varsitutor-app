package dev.dicesystems.varsitutor.data.remote.responses

data class Errors(
    val device_name: List<String>?,
    val email: List<String>?,
    val password: List<String>?
)