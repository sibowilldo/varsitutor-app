package dev.dicesystems.varsitutor.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material.icons.rounded.MoreTime
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.dicesystems.varsitutor.data.models.ApplicationModel
import dev.dicesystems.varsitutor.viewmodels.MainViewModel
import java.util.Locale

@Composable
fun ApplicationList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel
) {
    val applicationList by remember { viewModel.applicationsList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val itemsCount = applicationList.size

    if (itemsCount > 0) {
        LazyColumn(modifier = modifier, contentPadding = PaddingValues(top = 0.dp)) {
            items(itemsCount) {
                ApplicationItem(
                    application = applicationList[it],
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

    } else {

        LottieFile(
            lottieLocation = "https://assets9.lottiefiles.com/datafiles/vhvOcuUkH41HdrL/data.json",
            headingText = "Nothing Here!",
            helpText = "Your applications will appear here. "
        )
    }


}

@Composable
fun ApplicationItem(
    application: ApplicationModel,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Column() {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("# ${application.id}")
                TextWithIcon(
                    text = application.created_at.human,
                    textColor = MaterialTheme.colorScheme.onBackground,
                    icon = Icons.Rounded.MoreTime,
                    iconDescription = ""
                )
            }
            Divider(color =MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    application.vacancy.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    TextWithIcon(
                        text = application.vacancy.category,
                        textColor = MaterialTheme.colorScheme.onBackground,
                        icon = Icons.Rounded.Folder,
                        iconDescription = ""
                    )
                    TextWithIcon(
                        text = application.vacancy.user,
                        textColor = MaterialTheme.colorScheme.onBackground,
                        icon = Icons.Rounded.Person,
                        iconDescription = ""
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextWithIcon(
                        text = application.vacancy.department,
                        textColor = MaterialTheme.colorScheme.onBackground,
                        icon = Icons.Rounded.Apartment,
                        iconDescription = ""
                    )
                    TextWithIcon(
                        text = application.status.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        textColor = MaterialTheme.colorScheme.onBackground,
                        icon = Icons.Rounded.CheckCircle,
                        iconDescription = ""
                    )
                }
            }
        }
    }
}
