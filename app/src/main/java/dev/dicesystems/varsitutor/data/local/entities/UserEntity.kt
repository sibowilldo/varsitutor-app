package dev.dicesystems.varsitutor.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.dicesystems.varsitutor.data.remote.responses.Joined

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    @ColumnInfo(name = "internal_id") val internalId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "given_name") val givenName: String,
    @ColumnInfo(name = "family_name") val familyName: String,
    @ColumnInfo(name = "contact_number") val contactNumber: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "province_city") val provinceCity: String,
    @ColumnInfo(name = "profile_photo_url") val profilePhotoUrl: String ? = null,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "applications_count") val applicationsCount: Int = 0,
    @ColumnInfo(name = "joined") val joined: String? = null,
    @ColumnInfo(name = "verified") val verified: Boolean = false,
    @ColumnInfo(name = "token") val token: String
)
