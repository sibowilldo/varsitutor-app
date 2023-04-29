package dev.dicesystems.varsitutor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.AndroidEntryPoint
import dev.dicesystems.varsitutor.bottomnavigation.AppNavigation
import dev.dicesystems.varsitutor.ui.theme.VarsitutorTheme
import dev.dicesystems.varsitutor.util.PreferenceManager

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VarsitutorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .scrollable(
                            state = rememberScrollState(),
                            orientation = Orientation.Horizontal
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        val context = LocalContext.current
                        val preferenceManager = PreferenceManager(context)
                        Log.d("MAIN ACTIVITY TAG", "onCreate: ${preferenceManager.getToken()}")
                        if (preferenceManager.getToken() == null) {
                            context.startActivity(Intent(context, LoginActivity::class.java))
                        } else {
                            Toast.makeText(context, "Signing In...", Toast.LENGTH_SHORT).show()
                            AppNavigation()
                        }
                    }
                }
            }
        }
    }
}