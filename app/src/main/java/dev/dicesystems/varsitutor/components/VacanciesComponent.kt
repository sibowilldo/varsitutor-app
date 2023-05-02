package dev.dicesystems.varsitutor.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dicesystems.varsitutor.bottomnavigation.Screens
import dev.dicesystems.varsitutor.data.models.VacancyModel
import dev.dicesystems.varsitutor.viewmodels.MainViewModel


@Composable
fun VacancyList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {
    val vacancyList by remember { viewModel.vacancyList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(modifier = modifier, contentPadding = PaddingValues(top = 0.dp)) {
        val itemsCount = vacancyList.size

        items(itemsCount) {
            if (it >= itemsCount - 1 && !endReached) {
                viewModel.loadVacancyPaginatedList()
            }
            VacancyItem(
                vacancy = vacancyList[it],
                navController = navController,
                viewModel = viewModel
            )
        }
    }

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxWidth(),
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

}

@Composable
fun VacancyItem(
    vacancy: VacancyModel,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {

    val responseMessage by remember { viewModel.responseMessage }
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
                        vacancy.department,
                        vacancy.created_at.human,
                        vacancy.expires_at.date_time,
                        vacancy.location
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