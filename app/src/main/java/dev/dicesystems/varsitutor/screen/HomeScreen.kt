package dev.dicesystems.varsitutor.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.dicesystems.varsitutor.bottomnavigation.Screens
import dev.dicesystems.varsitutor.components.HomeTabBar
import dev.dicesystems.varsitutor.components.SearchBar
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.viewmodels.VacancyListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(24.dp)) {
                SearchBar(modifier = Modifier.padding(vertical = 24.dp))
                Column(
                    modifier = Modifier
                        .padding(top = 18.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Vacancies",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Left
                    )
                }
            }

        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            StudentHomeScreen(navController = navController)
        }
    }
}

@Composable
fun StudentHomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HomeTabBar(navController = navController)
    }
}

@Composable
fun TeacherHomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Teacher Home Screen")
    }
}

@Composable
fun HodHomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "HOD Screen")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
                ),
                modifier = Modifier
                    .height(120.dp)
                    .padding(bottom = 25.dp)
                    .clickable {
                    }
            )
            {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1F),

                    ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.background
                            )
                            .size(60.dp)
                    ) {
                        Text(text = "🧑‍💻", fontSize = 22.sp)
                    }
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .weight(4F)
                            .fillMaxSize()
                    ) {
                        Column {
                            Text(
                                text = "vacancy.title",
                                modifier = Modifier,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                            )
                            Text(
                                text = "vacancy.department",
                                modifier = Modifier,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "vacancy.category",
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    letterSpacing = 0.sp
                                ),
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
                            )
                            Icon(
                                imageVector = Icons.Rounded.Circle,
                                contentDescription = "Circle Icon",
                                modifier = Modifier.size(4.dp)
                            )
                            Text(
                                text = "vacancy.location",
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    letterSpacing = 0.sp
                                ),
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
                            )

                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        val context = LocalContext.current
                        IconButton(
                            modifier = Modifier
                                .size(36.dp)
                                .weight(1F)
                                .width(20.dp),
                            onClick = {
                                Toast.makeText(context, "Loved it!", Toast.LENGTH_LONG).show()
                            }) {
                            Icon(
                                imageVector = Icons.Rounded.FavoriteBorder,
                                contentDescription = "Heart Icon"
                            )
                        }

                        Text(
                            text = "vacancy.created_at.human",
                            maxLines = 1,
                            modifier = Modifier.padding(bottom = 10.dp),
                            style = MaterialTheme.typography.bodySmall.copy(
                                letterSpacing = 0.sp
                            )
                        )
                    }
                }
            }


        }

    }
}

@Composable
fun VacancyList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: VacancyListViewModel = hiltViewModel()
) {
    val vacancyList by remember { viewModel.vacancyList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    if (isLoading) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Waiting for Vacancies...",
                textAlign = TextAlign.Center
            )
            LinearProgressIndicator()
        }
    }

    LazyColumn(modifier = modifier, contentPadding = PaddingValues(top = 0.dp)) {
        val itemsCount = vacancyList.size

        items(itemsCount) {
            if (it >= itemsCount - 1 && !endReached) {
                viewModel.loadVacancyPaginatedList()
            }
            VacancyItem(
                vacancy = vacancyList[it],
                navController = navController
            )
        }
    }
}

@Composable
fun VacancyItem(
    vacancy: VacancyModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: VacancyListViewModel = hiltViewModel()
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
        ),
        modifier = Modifier
            .height(120.dp)
            .padding(bottom = 25.dp)
            .clickable {
                navController.navigate(
                    route = Screens.VacancyDetails.withArgs(
                        vacancy.title,
                        vacancy.description,
                        vacancy.department,
                        vacancy.category,
                        vacancy.type,
                        vacancy.user,
                        vacancy.location,
                        //vacancy.expires_at,
                        //vacancy.created_at,
                        )
                )
            }
    )
    {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .weight(1F),

            ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = listOf(MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.secondaryContainer,
                            MaterialTheme.colorScheme.tertiaryContainer,
                            ).random()
                    )
                    .size(60.dp)
            ) {
                Text(text = "🧑‍💻", fontSize = 22.sp)
            }
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .weight(4F)
                    .fillMaxSize()
            ) {
                Column {
                    Text(
                        text = vacancy.title,
                        modifier = Modifier,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                    )
                    Text(
                        text = vacancy.department,
                        modifier = Modifier,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = vacancy.category,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.sp
                        ),
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
                    )
                    Icon(
                        imageVector = Icons.Rounded.Circle,
                        contentDescription = "Circle Icon",
                        modifier = Modifier.size(4.dp)
                    )
                    Text(
                        text = vacancy.location,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            letterSpacing = 0.sp
                        ),
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.35f)
                    )

                }
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                val context = LocalContext.current
                IconButton(
                    modifier = Modifier
                        .size(36.dp)
                        .weight(1F)
                        .width(20.dp),
                    onClick = {
                        Toast.makeText(context, "Loved it!", Toast.LENGTH_LONG).show()
                    }) {
                    Icon(
                        imageVector = Icons.Rounded.FavoriteBorder,
                        contentDescription = "Heart Icon"
                    )
                }

                Text(
                    text = vacancy.created_at.human,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 10.dp),
                    style = MaterialTheme.typography.bodySmall.copy(
                        letterSpacing = 0.sp
                    )
                )
            }
        }
    }
}
