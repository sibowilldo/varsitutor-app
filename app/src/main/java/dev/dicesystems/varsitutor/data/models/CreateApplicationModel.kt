package dev.dicesystems.varsitutor.data.models

data class CreateApplicationModel(
    val vacancy_id: Int,
    val user_id: Int,
    val email: String,
    val contact_number: String,
    val motivation: String,
    val job_title: String,
    val duration: String,
    val company_department: String
)
