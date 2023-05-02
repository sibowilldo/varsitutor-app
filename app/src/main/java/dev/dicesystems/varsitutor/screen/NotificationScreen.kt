package dev.dicesystems.varsitutor.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.MoreTime
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.dicesystems.varsitutor.components.CustomTopAppBar
import dev.dicesystems.varsitutor.components.LottieFile
import dev.dicesystems.varsitutor.components.TextWithIcon
import dev.dicesystems.varsitutor.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun NotificationScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val notificationList by remember { viewModel.notificationList }

    val isRefreshing by viewModel.isLoading
    val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = {
        viewModel.refreshNotificationList()
    })
    val itemsCount = notificationList.size
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Notifications",
                showBackIcon = true
            )
        },
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (notificationList.isNotEmpty()) {
                    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
                        LazyColumn(
                            modifier = Modifier,
                            contentPadding = PaddingValues(top = 0.dp)
                        ) {

                            items(itemsCount) {
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.onBackground.copy(alpha= 0.05f)
                                    ),
                                    modifier = Modifier.padding(
                                        vertical = 8.dp,
                                        horizontal = 22.dp
                                    )
                                ) {
                                    Text(
                                        notificationList[it].title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp, vertical = 8.dp),
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                    )
                                    Divider(color =MaterialTheme.colorScheme.onBackground.copy(alpha= 0.1f))
                                    Column(
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp,
                                            vertical = 8.dp
                                        )
                                    ) {
                                        Text(
                                            notificationList[it].body,
                                            modifier = Modifier,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .fillMaxWidth()
                                        ) {
                                            if (notificationList[it].read_at == null) {
                                                TextWithIcon(
                                                    text = "Unread",
                                                    textColor = MaterialTheme.colorScheme.onSurface.copy(
                                                        alpha = 0.4f
                                                    ),
                                                    icon = Icons.Rounded.Check,
                                                    iconDescription = "Time Icon"
                                                )
                                            } else {
                                                TextWithIcon(
                                                    text = notificationList[it].created_at.human,
                                                    textColor = MaterialTheme.colorScheme.onSurface.copy(
                                                        alpha = 0.4f
                                                    ),
                                                    icon = Icons.Rounded.DoneAll,
                                                    iconDescription = "Time Icon"
                                                )
                                            }

                                            TextWithIcon(
                                                text = notificationList[it].created_at.human,
                                                textColor = MaterialTheme.colorScheme.onSurface.copy(
                                                    alpha = 0.4f
                                                ),
                                                icon = Icons.Rounded.MoreTime,
                                                iconDescription = "Time Icon"
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        PullRefreshIndicator(
                            isRefreshing,
                            pullRefreshState,
                            Modifier.align(Alignment.TopCenter)
                        )
                    }
                } else {
                    LottieFile(
                        lottieLocation = "https://assets8.lottiefiles.com/packages/lf20_KZ1htY.json",
                        headingText = "Nothing Yet!", helpText = "You don't have any notifications."
                    )
                }
            }
        }
    }
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES, showSystemUi = true, showBackground = true)
fun PreviewNotification() {
    Column() {
        Card() {
            Text(
                "Notification Title",
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            Divider()
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    "Notification  Body",
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    TextWithIcon(
                        text = "created",
                        textColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                        icon = Icons.Rounded.MoreTime,
                        iconDescription = "Time Icon"
                    )
                }
            }
        }
    }
}