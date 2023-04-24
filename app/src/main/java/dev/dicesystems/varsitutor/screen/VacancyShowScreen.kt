package dev.dicesystems.varsitutor.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dicesystems.varsitutor.R
import dev.dicesystems.varsitutor.components.CustomTopAppBar
import dev.dicesystems.varsitutor.components.DefaultButton
import dev.dicesystems.varsitutor.components.TextWithIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyShowScreen(
    navController: NavController,
    title: String?,
    description: String?
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
        Box(modifier = Modifier.padding(it), contentAlignment = Alignment.BottomCenter) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .padding(16.dp).padding(bottom = 68.dp)
                    .fillMaxSize()
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            ) {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.0f)
                    ),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(vertical = 16.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1F)) {
                                Row(modifier = Modifier.height(48.dp)) {
                                    Column(modifier = Modifier.padding(8.dp).weight(3F)) {
                                        Text(text = "Category")
                                        Text(text = "Permanent")
                                    }
                                    Icon(
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(1F)
                                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                                            .padding(8.dp),
                                        imageVector = Icons.Rounded.Folder,
                                        contentDescription = "Folder Icon"
                                    )
                                }

                            }
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1F)) {
                                Row(modifier = Modifier.height(48.dp)) {
                                    Column(modifier = Modifier.padding(8.dp).weight(3F))  {
                                        Text(text = "Category")
                                        Text(text = "Permanent")
                                    }
                                    Icon(
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                                            .padding(8.dp),
                                        imageVector = Icons.Rounded.Folder,
                                        contentDescription = "Folder Icon"
                                    )
                                }

                            }

                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F)) {
                                Row(modifier = Modifier.height(48.dp)) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Text(text = "Category")
                                        Text(text = "Permanent")
                                    }
                                    Icon(
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .weight(1F)
                                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                                            .padding(8.dp),
                                        imageVector = Icons.Rounded.Folder,
                                        contentDescription = "Folder Icon"
                                    )
                                }

                            }
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F)) {
                                Row(modifier = Modifier.height(48.dp)) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Text(text = "Category")
                                        Text(text = "Permanent")
                                    }
                                    Icon(
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                                            .padding(8.dp),
                                        imageVector = Icons.Rounded.Folder,
                                        contentDescription = "Folder Icon"
                                    )
                                }

                            }
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .weight(1F)) {
                                Row(modifier = Modifier.height(48.dp)) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        Text(text = "Category")
                                        Text(text = "Permanent")
                                    }
                                    Icon(
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                                            .padding(8.dp),
                                        imageVector = Icons.Rounded.Folder,
                                        contentDescription = "Folder Icon"
                                    )
                                }

                            }

                        }
                        TextWithIcon(
                            text = "Category", textColor = Color.LightGray, icon =
                            Icons.Rounded.CalendarToday, iconDescription = ""
                        )
                        TextWithIcon(
                            text = "Type", textColor = Color.LightGray, icon =
                            Icons.Rounded.CalendarToday, iconDescription = ""
                        )

                        TextWithIcon(
                            text = "User", textColor = Color.LightGray, icon =
                            Icons.Rounded.CalendarToday, iconDescription = ""
                        )
                        TextWithIcon(
                            text = "Department", textColor = Color.LightGray, icon =
                            Icons.Rounded.CalendarToday, iconDescription = ""
                        )
                        TextWithIcon(
                            text = "Location", textColor = Color.LightGray, icon =
                            Icons.Rounded.CalendarToday, iconDescription = ""
                        )

                        TextWithIcon(
                            text = "Expires", textColor = Color.LightGray, icon =
                            Icons.Rounded.CalendarToday, iconDescription = ""
                        )
                        TextWithIcon(
                            text = "Created", textColor = Color.LightGray, icon =
                            Icons.Rounded.CalendarToday, iconDescription = ""
                        )
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
                    onClick = { /*TODO*/ },
                    buttonText = stringResource(id = R.string.button_apply_now),
                    textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewVacancyDetails() {
    Box(modifier = Modifier, contentAlignment = Alignment.BottomCenter) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(16.dp).padding(bottom = 68.dp)
                .fillMaxSize()
                .verticalScroll(
                    state = rememberScrollState()
                )
        ) {
            Text(
                text = "Details",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.0f)
                ),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
                            ),
                            modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)) {
                            Row(modifier = Modifier.height(48.dp)) {
                                Column(modifier = Modifier.padding(8.dp).weight(3F)) {
                                    Text(text = "Category")
                                    Text(text = "Permanent")
                                }
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1F)
                                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                                        .padding(8.dp),
                                    imageVector = Icons.Rounded.Folder,
                                    contentDescription = "Folder Icon"
                                )
                            }

                        }
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.3f)
                            ),
                            modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)) {
                            Row(modifier = Modifier.height(48.dp)) {
                                Column(modifier = Modifier.padding(8.dp).weight(3F))  {
                                    Text(text = "Category")
                                    Text(text = "Permanent")
                                }
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                                        .padding(8.dp),
                                    imageVector = Icons.Rounded.Folder,
                                    contentDescription = "Folder Icon"
                                )
                            }

                        }

                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)) {
                            Row(modifier = Modifier.height(48.dp)) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(text = "Category")
                                    Text(text = "Permanent")
                                }
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1F)
                                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                                        .padding(8.dp),
                                    imageVector = Icons.Rounded.Folder,
                                    contentDescription = "Folder Icon"
                                )
                            }

                        }
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)) {
                            Row(modifier = Modifier.height(48.dp)) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(text = "Category")
                                    Text(text = "Permanent")
                                }
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                                        .padding(8.dp),
                                    imageVector = Icons.Rounded.Folder,
                                    contentDescription = "Folder Icon"
                                )
                            }

                        }
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .weight(1F)) {
                            Row(modifier = Modifier.height(48.dp)) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(text = "Category")
                                    Text(text = "Permanent")
                                }
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .background(color = MaterialTheme.colorScheme.primaryContainer)
                                        .padding(8.dp),
                                    imageVector = Icons.Rounded.Folder,
                                    contentDescription = "Folder Icon"
                                )
                            }

                        }

                    }
                    TextWithIcon(
                        text = "Category", textColor = Color.LightGray, icon =
                        Icons.Rounded.CalendarToday, iconDescription = ""
                    )
                    TextWithIcon(
                        text = "Type", textColor = Color.LightGray, icon =
                        Icons.Rounded.CalendarToday, iconDescription = ""
                    )

                    TextWithIcon(
                        text = "User", textColor = Color.LightGray, icon =
                        Icons.Rounded.CalendarToday, iconDescription = ""
                    )
                    TextWithIcon(
                        text = "Department", textColor = Color.LightGray, icon =
                        Icons.Rounded.CalendarToday, iconDescription = ""
                    )
                    TextWithIcon(
                        text = "Location", textColor = Color.LightGray, icon =
                        Icons.Rounded.CalendarToday, iconDescription = ""
                    )

                    TextWithIcon(
                        text = "Expires", textColor = Color.LightGray, icon =
                        Icons.Rounded.CalendarToday, iconDescription = ""
                    )
                    TextWithIcon(
                        text = "Created", textColor = Color.LightGray, icon =
                        Icons.Rounded.CalendarToday, iconDescription = ""
                    )
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
                text = "We are seeking a passionate, detail-oriented Research Assistant to aid our project by preparing interviews and summarizing results. You will also work with undergraduate students on the team who can help bring your expertise into their papers as they complete them for publication in top academic journals.\n" +
                        "Ultimately, you will be responsible for managing multiple tasks, but understand when one is more important than another to complete it on time.\n" +
                        "Responsibilities\n" +
                        "Maintain quality standards to preserve the integrity of data and findings\n" +
                        "Schedule and conduct interviews\n" +
                        "Select a place to conduct interviews and obtain permission from all participants\n" +
                        "Analyze data using various statistical methods\n" +
                        "Write reports to summarize data and the implications of the results\n" +
                        "Requirements and skills\n" +
                        "Proven work experience as a Research Assistant or similar role\n" +
                        "Excellent administrative skills\n" +
                        "Knowledge of research methods\n" +
                        "Ability to work under pressure\n" +
                        "Excellent time management skills\n" +
                        "A degree in the field related to the study is preferred",

                )
        }
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            DefaultButton(
                onClick = { /*TODO*/ },
                buttonText = stringResource(id = R.string.button_apply_now),
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}