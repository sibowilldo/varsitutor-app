package dev.dicesystems.varsitutor.data.remote.requests

data class LoginRequest(val email: String, val password: String, val device_name: String)
