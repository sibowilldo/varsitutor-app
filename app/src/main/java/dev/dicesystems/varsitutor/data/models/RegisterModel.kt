package dev.dicesystems.varsitutor.data.models

data class RegisterModel(
    val internal_identification: String,
    val given_name: String,
    val family_name: String,
    val contact_number: String,
    val province_city: String,
    val password: String,
    val password_confirmation: String,
    val device_name: String
)
