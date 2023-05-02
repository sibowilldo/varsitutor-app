package dev.dicesystems.varsitutor.data.remote.requests

import java.io.File

data class ApplicationRequest(
    val vacancy_id: Int,
    val user_id: Int,
    val contact_number: String,
    val email: String,
    val job_title: String,
    val duration: Int,
    val company_department: String,
    val motivation: String,
    val attachment: File,
)
