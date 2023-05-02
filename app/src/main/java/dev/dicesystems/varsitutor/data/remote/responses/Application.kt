package dev.dicesystems.varsitutor.data.remote.responses

data class Application(
    val additional_information: AdditionalInformation,
    val attachments: List<Any>,
    val contact_number: String,
    val created_at: CreatedAt,
    val email: String,
    val id: Int,
    val status: String,
    val vacancy: VacancyX
)