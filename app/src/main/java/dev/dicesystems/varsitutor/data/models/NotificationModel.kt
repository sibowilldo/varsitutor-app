package dev.dicesystems.varsitutor.data.models

import dev.dicesystems.varsitutor.data.remote.responses.Notifications

data class NotificationModel(
    val body: String,
    val created_at: Notifications.Notification.CreatedAt,
    val id: String,
    val read_at: Notifications.Notification.ReadAt?,
    val title: String,
    val updated_at: Notifications.Notification.UpdatedAt
)