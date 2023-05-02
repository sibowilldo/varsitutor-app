package dev.dicesystems.varsitutor.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import dev.dicesystems.varsitutor.R
import dev.dicesystems.varsitutor.bottomnavigation.Screens
import dev.dicesystems.varsitutor.components.CustomTopAppBar
import dev.dicesystems.varsitutor.components.DefaultButton
import dev.dicesystems.varsitutor.components.TextWithIcon
import dev.dicesystems.varsitutor.viewmodels.MainViewModel

@Composable
fun VacancyShowScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController,
    id: Int?,
    title: String?,
    description: String?,
    department: String?,
    created_at: String?,
    expires_at: String?,
    location: String?,
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "$title",
                showBackIcon = true
            )
        },
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(color = MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .padding(bottom = 68.dp)
                    .fillMaxSize()
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            ) {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
                )

                val cardHeight = 180.dp
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF4f71b8).copy(alpha = 0.1f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight)
                ) {
                    Box(modifier = Modifier) {
                        Image(
                            alignment = Alignment.TopEnd,
                            contentScale = ContentScale.Inside,
                            painter = painterResource(id = R.drawable.accounting),
                            contentDescription = "Background Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .height(cardHeight),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            TextWithIcon(
                                text = "$department", textColor = Color.DarkGray, icon =
                                Icons.Rounded.Apartment, iconDescription = ""
                            )
                            TextWithIcon(
                                text = "$created_at", textColor = Color.DarkGray, icon =
                                Icons.Rounded.CalendarToday, iconDescription = ""
                            )

                            TextWithIcon(
                                text = "$expires_at", textColor = Color.DarkGray, icon =
                                Icons.Rounded.Timer, iconDescription = ""
                            )
                            TextWithIcon(
                                text = "$location", textColor = Color.DarkGray, icon =
                                Icons.Rounded.LocationCity, iconDescription = ""
                            )
                        }
                    }
                }
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
                )
                Text(
// Description
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 24.sp,
                    text = "$description",

                    )
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                DefaultButton(
                    onClick = {
                        navController.navigate(
                            route = Screens.CreateApplication.withArgs(
                                title.toString(),
                                        id.toString(), )
                        )
                    },
                    buttonText = stringResource(id = R.string.button_apply_now),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.surface,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVacancyDetails() {

}