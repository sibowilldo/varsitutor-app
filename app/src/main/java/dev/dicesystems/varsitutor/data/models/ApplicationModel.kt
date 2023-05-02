package dev.dicesystems.varsitutor.data.models

import dev.dicesystems.varsitutor.data.remote.responses.AdditionalInformation
import dev.dicesystems.varsitutor.data.remote.responses.CreatedAt
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX

data class ApplicationModel(
    val additional_information: AdditionalInformation,
    val attachments: List<Any>,
    val contact_number: String,
    val created_at: CreatedAt,
    val email: String,
    val id: Int,
    val status: String,
    val vacancy: VacancyX
)