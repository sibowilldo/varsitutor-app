package dev.dicesystems.varsitutor.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Circle
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.MoreTime
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import dev.dicesystems.varsitutor.bottomnavigation.Screens
import dev.dicesystems.varsitutor.components.CustomTopAppBar
import dev.dicesystems.varsitutor.components.LottieFile
import dev.dicesystems.varsitutor.components.TextWithIcon
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.data.remote.responses.VacancyX
import dev.dicesystems.varsitutor.viewmodels.MainViewModel
import dev.dicesystems.varsitutor.viewmodels.ProfileViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavouriteScreen(navController: NavController,
                    viewModel: MainViewModel = hiltViewModel(),
                    profileViewModel: ProfileViewModel = hiltViewModel()
){
    profileViewModel.getUserInfo(navController.context)
    val userState by profileViewModel.userState.collectAsState()

    val favoriteList by remember {viewModel.favoritesList}

    val isRefreshing by viewModel.isLoading
    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        viewModel.refreshNotificationList()
    })
    val itemsCount = favoriteList.size

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Favorite Vacancies",
                showBackIcon = true
            )
        },
    ) { paddingValues ->
        Surface(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (favoriteList.isNotEmpty()) {
                    Box(modifier = Modifier.pullRefresh(pullRefreshState).fillMaxSize().padding(horizontal = 22.dp)) {
                        LazyColumn(
                            modifier = Modifier,
                            contentPadding = PaddingValues(top = 0.dp)
                        ) {

                            items(itemsCount) {
                                    FavoriteItem(
                                        vacancy = favoriteList[it].vacancy,
                                        navController = navController,
                                        viewModel = viewModel
                                    )

                            }
                        }
                        PullRefreshIndicator(
                            isRefreshing,
                            pullRefreshState,
                            Modifier.align(Alignment.TopCenter)
                        )
                    }
                } else {
                LottieFile(lottieLocation = "https://assets3.lottiefiles.com/packages/lf20_ydo1amjm.json",
                    headingText = "You don't like anything", helpText = "Seems you haven't found the one yet!")
                }
            }
        }
    }
}

@Composable
fun FavoriteItem(
    vacancy: VacancyX,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f),
        ),
        modifier = Modifier
            .height(120.dp)
            .padding(bottom = 25.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            )
            .clickable {
                navController.navigate(
                    route = Screens.VacancyDetails.withArgs(
                        vacancy.id.toString(),
                        vacancy.title,
                        vacancy.description,
                        vacancy.department
                    )
                )
            }
    )
    {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 12.dp)
                .weight(1F),

            ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f),
                            MaterialTheme.colorScheme.error.copy(alpha = 0.2f),
                        ).random()
                    )
                    .size(60.dp)
            ) {
                Text(
                    text = listOf(
                        "🧑‍💻",
                        " 👩🏻‍⚖️",
                        "\uD83E\uDDD1\uD83C\uDFFF\u200D\uD83C\uDF73",
                        "\uD83D\uDC68\u200D\uD83D\uDD27",
                        "\uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDD2C",
                        "\uD83D\uDC69\uD83C\uDFFF\u200D\uD83C\uDFA8"
                    ).random(), fontSize = 22.sp
                )
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
                        color = MaterialTheme.colorScheme.primary,
                        text = vacancy.department,
                        modifier = Modifier,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
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
                IconButton(
                    modifier = Modifier
                        .size(42.dp)
                        .weight(1F)
                        .width(20.dp),
                    onClick = {
                        viewModel.doToggleFavorite(vacancy.id, navController.context)
                    }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.7f),
                        imageVector = Icons.Rounded.Favorite,
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