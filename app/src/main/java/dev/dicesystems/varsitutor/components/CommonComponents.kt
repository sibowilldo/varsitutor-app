package dev.dicesystems.varsitutor.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppVersion(){
    Text("Version 2023.1.0.0",
        color = Color.LightGray,
        style = MaterialTheme.typography.bodyMedium)
}