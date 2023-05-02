package dev.dicesystems.varsitutor.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.PersonPinCircle
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.dicesystems.varsitutor.R
import dev.dicesystems.varsitutor.components.CustomTopAppBar
import dev.dicesystems.varsitutor.viewmodels.MainViewModel
import dev.dicesystems.varsitutor.viewmodels.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Profile",
                showBackIcon = true
            )
        },
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
        ) {
            GenerateProfileScreen(navController = navController)
        }
    }
}


@Composable
fun GenerateProfileScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    profileViewModel.getUserInfo(navController.context)
    val userState by profileViewModel.userState.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        Box() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(58.dp),
                    model = "${userState?.profilePhotoUrl}", contentDescription = "")

                Column() {
                    Text(
                        text = "${userState?.name}",
                        style = MaterialTheme.typography.headlineMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            text =  "${userState?.joined}",
                            modifier = Modifier,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            text = "Applications Sent ${userState?.applicationsCount}",
                            modifier = Modifier,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }// User Details

        Box() {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Rounded.Mail,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "Mail Icon"
                        )
                        Text(text = "${userState?.email}")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Rounded.PhoneAndroid,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "Android Phone Icon"
                        )
                        Text(text = "${userState?.contactNumber}")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Rounded.PersonPinCircle,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "Person Pin Icon"
                        )
                        Text(text = "${userState?.provinceCity}")
                    }
                }
            }
        } // User Information

        Box() {
            Column() {

                Text(
                    text = stringResource(id = R.string.resumes),
                    modifier = Modifier,
                    style = MaterialTheme.typography.headlineMedium
                )
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        ProfileAttachments {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                painter = painterResource(id = R.drawable.ph_file_pdf_light),
                                contentDescription = "Mail Icon"
                            )
                            Text(
                                text = "Final_Resume.pdf",
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        } // Resumes

        Box() {
            Column() {
                Text(
                    text = stringResource(id = R.string.additional_documents),
                    modifier = Modifier,
                    style = MaterialTheme.typography.headlineSmall
                )
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        ProfileAttachments {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                painter = painterResource(id = R.drawable.ph_file_pdf_light),
                                contentDescription = "Mail Icon"
                            )
                            Text(
                                text = "Matric_Cerficate.pdf",
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        } // Additional Documents
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewProfile() {
    val navController = NavController(LocalContext.current)
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Profile",
                showBackIcon = true
            )
        },
    ) {
        Surface(modifier = Modifier.padding(it)) {
            GenerateProfileScreen(navController)
        }
    }
}

@Composable
fun ProfileAttachments(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.widthIn(max = 52.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}