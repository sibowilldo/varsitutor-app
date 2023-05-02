package dev.dicesystems.varsitutor.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieFile(
    lottieLocation: String,
    headingText: String,
    helpText: String?
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(lottieLocation))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
        Text(headingText.uppercase(), style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black), color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
        Text(text = "$helpText", style = MaterialTheme.typography.bodySmall,textAlign = TextAlign.Center , color = MaterialTheme.colorScheme.tertiary, modifier = Modifier.widthIn(max = 180.dp))
    }
}