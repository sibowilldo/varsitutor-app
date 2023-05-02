package dev.dicesystems.varsitutor.screen

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.CloudUpload
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dev.dicesystems.varsitutor.R
import dev.dicesystems.varsitutor.bottomnavigation.BottomBarScreen
import dev.dicesystems.varsitutor.components.CustomTopAppBar
import dev.dicesystems.varsitutor.components.DefaultButton
import dev.dicesystems.varsitutor.components.InputField
import dev.dicesystems.varsitutor.data.models.CreateApplicationModel
import dev.dicesystems.varsitutor.viewmodels.MainViewModel
import dev.dicesystems.varsitutor.viewmodels.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun CreateApplicationScreen(
    viewModel: MainViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navController: NavController,
    title: String?,
    vacancy_id: Int?
) {
    profileViewModel.getUserInfo(navController.context)
    val userState by profileViewModel.userState.collectAsState()
    val createApplicationState by viewModel.createApplicationState.collectAsState()

    val mutableContactNumber = remember { mutableStateOf("") }
    val mutableEmail = remember { mutableStateOf("") }
    val mutableJobTitle = remember { mutableStateOf("IP Tutor") }
    val mutableDuration = remember { mutableStateOf("9") }
    val mutableCompanyDepartment = remember { mutableStateOf("DUT, IT") }
    val mutableMotivation = remember { mutableStateOf("I don't have any motivation, I just work") }
    val mutableVacancyId = remember { mutableStateOf(0) }
    val mutableUserId = remember { mutableStateOf(0) }

    mutableContactNumber.value = userState?.contactNumber.toString()
    mutableEmail.value = userState?.email.toString()
    mutableUserId.value = userState?.id ?: 0
    mutableVacancyId.value = vacancy_id ?: 0

    val permissionState = rememberPermissionState(
        permission = Manifest.permission.READ_MEDIA_IMAGES
    )

    val context = navController.context

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult =
        {
            viewModel.onFilePathsListChange(it, context)

        })
    val state = viewModel.state

    SideEffect {
        permissionState.launchPermissionRequest()
    }
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Apply for \"$title\"",
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
                    text = "Confirm Your Details",
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
                        InputField(
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableContactNumber,
                            labelId = "Contact Number",
                            keyboardType = KeyboardType.Phone,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.PhoneAndroid,
                                    contentDescription = "Android Phone Icon"
                                )
                            })
                        InputField(
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableEmail,
                            labelId = "Email",
                            keyboardType = KeyboardType.Email,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Email,
                                    contentDescription = "Email Icon"
                                )
                            })

                        Box(
                            modifier = Modifier
                                .padding(top = 18.dp)
                                .background(
                                    color = Color.LightGray.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable {
                                    if (permissionState.status.isGranted) {
                                        filePickerLauncher.launch("application/pdf")
                                    } else {
                                        permissionState.launchPermissionRequest()
                                    }
                                }
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(18.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = Modifier.size(48.dp),
                                    imageVector = Icons.Rounded.CloudUpload,
                                    contentDescription = "Upload File Icon"
                                )
                                Text("Upload Resume/CV")
                            }
                        }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp), singleLine = false,
                            label = {
                                Text("Motivation")
                            },
                            value = mutableMotivation.value, onValueChange = { motivation ->
                                mutableMotivation.value = motivation
                            })

                        Divider(modifier = Modifier.padding(vertical = 18.dp))
                        Text(
                            text = "Additional Information",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
                        )
                        InputField(
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableJobTitle,
                            labelId = "Job Title",
                            keyboardType = KeyboardType.Text,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Badge,
                                    contentDescription = "Badge Icon"
                                )
                            })
                        InputField(
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableDuration,
                            labelId = "Duration",
                            keyboardType = KeyboardType.Number,
                            suffix = {
                                Text("Months")
                            },
                            placeholder = {
                                Text("6")
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Timer,
                                    contentDescription = "Timer Icon"
                                )
                            })
                        InputField(
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableCompanyDepartment,
                            labelId = "Company & Department",
                            keyboardType = KeyboardType.Text,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Apartment,
                                    contentDescription = "Email Icon"
                                )
                            })

                    }
                }
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                DefaultButton(
                    onClick = {
                        viewModel.doCreateApplication(
                            context = context,
                            CreateApplicationModel(
                                vacancy_id = mutableVacancyId.value,
                                user_id = mutableUserId.value,
                                email = mutableEmail.value,
                                contact_number = mutableContactNumber.value,
                                motivation = mutableMotivation.value,
                                job_title = mutableJobTitle.value,
                                duration = mutableDuration.value,
                                company_department = mutableCompanyDepartment.value,
                            )
                        )

                        navController.navigate(BottomBarScreen.Home.route)
                    },
                    buttonText = stringResource(id = R.string.button_send_application),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    )
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCreateApplication() {
    val context = LocalContext.current
    val navController = NavHostController(context)
    val viewModel: MainViewModel = viewModel()
    CreateApplicationScreen(
        navController = navController,
        title = "Job Opportunity",
        vacancy_id = 0
    )
}
