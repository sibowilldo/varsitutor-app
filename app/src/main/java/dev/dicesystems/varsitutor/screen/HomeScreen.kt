package dev.dicesystems.varsitutor.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.FilterList
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import dev.dicesystems.varsitutor.components.Faculties
import dev.dicesystems.varsitutor.components.HomeTabBar
import dev.dicesystems.varsitutor.components.SearchBar
import dev.dicesystems.varsitutor.viewmodels.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.padding(24.dp)) {
                SearchBar(modifier = Modifier.padding(vertical = 8.dp))
                Column(
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start
                ) {
                }
            }

        }, containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {

//            viewModel.doGetLoggedInUser(navController.context)
            StudentHomeScreen(navController = navController, viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StudentHomeScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        val facultyList = listOf(
            Faculties.Accounting,
            Faculties.Applied,
            Faculties.Arts,
            Faculties.Engineering,
            Faculties.Health,
            Faculties.Management,
        )
        HorizontalPager(
            modifier = Modifier.padding(bottom = 18.dp),
            count = facultyList.size,
            itemSpacing = 12.dp,
            state = rememberPagerState(),
        ) { index ->
            FacultyCard(facultyList[index])
        }

        HomeTabBar(navController = navController, viewModel = viewModel)
    }
}


@Composable
fun FacultyCard(faculty: Faculties) {
    val cardHeight = 180.dp
    Card(
        colors = CardDefaults.cardColors(containerColor = faculty.brandColor.copy(alpha = 0.1f)),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
    ) {
        Box(modifier = Modifier) {
            Image(
                alignment = Alignment.TopEnd,
                contentScale = ContentScale.Inside,
                painter = painterResource(id = faculty.cardBackgroundResource),
                contentDescription = faculty.facultyName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .height(cardHeight),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(text = "Faculty of".uppercase())
                    Text(
                        text = faculty.facultyName,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Vacancies (${faculty.vacancyCount})".uppercase(),
                        modifier = Modifier,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = faculty.brandColor,
                            contentColor = Color.White
                        ),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            modifier = Modifier.size(height = 18.dp, width = 18.dp),
                            imageVector = Icons.Rounded.FilterList,
                            contentDescription = "Filter Icon"
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
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
                        val scaffoldState = rememberScaffoldState()
                        val coroutineScope = rememberCoroutineScope()

                        Scaffold {
                            IconButton(
                                modifier = Modifier
                                    .padding(it)
                                    .size(36.dp)
                                    .weight(1F)
                                    .width(20.dp),
                                onClick = {
                                    coroutineScope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "You liked it!",
                                            actionLabel = "Close"
                                        )
                                    }
                                }) {
                                Icon(
                                    imageVector = Icons.Rounded.FavoriteBorder,
                                    contentDescription = "Heart Icon"
                                )
                            }
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
