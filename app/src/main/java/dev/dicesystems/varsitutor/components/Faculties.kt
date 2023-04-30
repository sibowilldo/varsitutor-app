package dev.dicesystems.varsitutor.components

import androidx.compose.ui.graphics.Color
import dev.dicesystems.varsitutor.R

sealed class Faculties(
    val facultyName: String,
    val vacancyCount: Int = 0,
    val brandColor: Color,
    val cardBackgroundResource: Int
) {
    object Accounting : Faculties(
        facultyName = "Accounting & Informatics",
        brandColor = Color(0xFF4f71b8),
        cardBackgroundResource = R.drawable.accounting
    )

    object Applied : Faculties(
        facultyName = "Applied Sciences",
        brandColor = Color(0xFFa66eaf),
        cardBackgroundResource = R.drawable.applied
    )

    object Arts : Faculties(
        facultyName = "Arts & Design",
        brandColor = Color(0xFFca261d),
        cardBackgroundResource = R.drawable.arts
    )

    object Engineering : Faculties(
        facultyName = "Engineering & Built Environment",
        brandColor = Color(0xFF1f8442),
        cardBackgroundResource = R.drawable.engineering
    )

    object Health : Faculties(
        facultyName = "Health Sciences",
        brandColor = Color(0xFF822576),
        cardBackgroundResource = R.drawable.health
    )

    object Management : Faculties(
        facultyName = "Management Sciences",
        brandColor = Color(0xFF7ec2c3),
        cardBackgroundResource = R.drawable.management
    )
}