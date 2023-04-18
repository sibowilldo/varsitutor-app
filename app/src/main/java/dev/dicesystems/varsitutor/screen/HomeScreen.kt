package dev.dicesystems.varsitutor.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FilterNone
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material.icons.rounded.MoreTime
import androidx.compose.material.icons.rounded.Timer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import dev.dicesystems.varsitutor.components.CustomTopAppBar
import dev.dicesystems.varsitutor.components.HomeTabBar
import dev.dicesystems.varsitutor.components.SearchBar
import dev.dicesystems.varsitutor.components.TextWithIcon
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.viewmodels.VacancyListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Home",
                showBackIcon = true
            )
        },
    ) {
        Surface(modifier = Modifier.padding(it)) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewScreen() {
    Surface() {
        Text(
            text = "Hello World",
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 300.dp)
                .placeholder(
                    shape = RoundedCornerShape(4.dp),
                    visible = true,
                    color = Color.LightGray,
                    highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
                ),
        )
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

    if(isLoading){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "Waiting for Vacancies...",
                textAlign = TextAlign.Center)
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
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate(route = "vacancy_show/${vacancy.title}")
            }
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(Color.Transparent),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(100.dp),
                            color = Color(0xFFDEDCEB)
                        )
                        .size(40.dp)
                ) {
                    Text(text = "🧑‍💻")
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(modifier = Modifier.padding(end = 30.dp)) {
                        Text(
                            text = vacancy.department,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {

                            TextWithIcon(
                                text = vacancy.type,
                                textColor = Color(0xFF8E86C3),
                                icon = Icons.Rounded.Folder,
                                iconDescription = "Folder Icon"
                            )

                            TextWithIcon(
                                text = vacancy.category,
                                textColor = Color(0xFF8E86C3),
                                icon = Icons.Rounded.FilterNone,
                                iconDescription = "Filter None Icon"
                            )

                        }
                    }
                }

                val context = LocalContext.current
                IconButton(
                    modifier = Modifier.size(20.dp),
                    onClick = {
                        Toast.makeText(context, "Loved it!", Toast.LENGTH_LONG).show()
                    }) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "Heart Icon"
                    )
                }

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = vacancy.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1F)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(start = 28.dp)
                ) {

                    TextWithIcon(
                        text = vacancy.created_at.human,
                        textColor = Color(0xFF6B6B6B),
                        icon = Icons.Rounded.MoreTime,
                        iconDescription = "More Time Icon"
                    )

                    TextWithIcon(
                        text = vacancy.expires_at.human,
                        textColor = Color(0xFF6B6B6B),
                        icon = Icons.Rounded.Timer,
                        iconDescription = "Timer Icon"
                    )
                }
            }

            Row() {

                Text(
                    text = vacancy.description,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Light,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1F)
                )
            }
        }
    }
}
