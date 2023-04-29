package dev.dicesystems.varsitutor.data.remote.responses

import androidx.room.ColumnInfo

data class UserX(
    val internal_id: String,
    val name: String ? = null,
    val given_name: String,
    val family_name: String,
    val contact_number: String,
    val province_city: String,
    val email: String ? = null,
    val profile_photo_url: String ? = null,
    val status: String ? = null,
    val applications_count: Int ? = null,
    val applications: List<Any> ? = null,
    val joined: Joined ? = null,
    val verified: Boolean = false
)