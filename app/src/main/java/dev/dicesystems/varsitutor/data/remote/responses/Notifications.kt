package dev.dicesystems.varsitutor.data.remote.responses

data class Notifications(
    val notifications: List<Notification>
) {
    data class Notification(
        val body: String,
        val created_at: CreatedAt,
        val id: String,
        val read_at: ReadAt?,
        val title: String,
        val updated_at: UpdatedAt
    ) {
        data class CreatedAt(
            val date_time: String,
            val human: String
        )

        data class ReadAt(
            val date_time: String,
            val human: String
        )

        data class UpdatedAt(
            val date_time: String,
            val human: String
        )
    }
}