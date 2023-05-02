package dev.dicesystems.varsitutor.screen

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.dicesystems.varsitutor.LoginActivity
import dev.dicesystems.varsitutor.R
import dev.dicesystems.varsitutor.bottomnavigation.BottomBarScreen
import dev.dicesystems.varsitutor.components.CustomTopAppBar
import dev.dicesystems.varsitutor.components.DefaultButton
import dev.dicesystems.varsitutor.components.ResetPasswordSheetContent
import dev.dicesystems.varsitutor.util.PreferenceManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreen(navController: NavController) {
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Hidden,
        skipHiddenState = false
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            ResetPasswordSheetContent(sheetState = bottomSheetState)
        }
    ) {
        GenerateSettings(navController, bottomSheetState)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateSettings(
    navController: NavController,
    sheetState: SheetState
) {
    val context = navController.context
    val preferenceManager = PreferenceManager(context)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Settings & More",
                showBackIcon = true
            )
        },
    ) {
        Box(modifier = Modifier.padding(it), contentAlignment = Alignment.BottomCenter) {
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
                Box {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = "Account".uppercase(),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                        )
                        Text(
                            text = "Logout".uppercase() + " sibongiseni@mail.com",
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                            modifier = Modifier.clickable {
                                preferenceManager.clearToken()

                                navController.navigate(BottomBarScreen.More.route) {
                                    popUpTo(navController.graph.id) {
                                        inclusive = true
                                    }
                                }
                                context.startActivity(Intent(context, LoginActivity::class.java))
                            }
                        )
                        Text(
                            text = "Reset Password".uppercase(),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    sheetState.expand()
                                }
                            }

                        )
                    }
                }
                Box {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                        Text(
                            text = "Notification Center".uppercase(),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "New Vacancies",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                                )
                                Text(
                                    modifier = Modifier.widthIn(max = 230.dp),
                                    text = "Be the first know when new vacancies are uploaded",
                                    color = Color.LightGray,
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
                                )
                            }

                            Checkbox(checked = true, onCheckedChange = {})
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Application Status Change",
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                                )
                                Text(
                                    modifier = Modifier.widthIn(max = 230.dp),
                                    text = "Get notified when a status of your application changes",
                                    color = Color.LightGray,
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal)
                                )
                            }

                            Checkbox(checked = false, onCheckedChange = {})
                        }
                    }
                }

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


                    }
                }

            }

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text(
                    "Danger Zone",
                    modifier = Modifier,
                    style = MaterialTheme.typography.headlineLarge
                )
                DefaultButton(
                    onClick = { /*TODO*/ },
                    buttonText = stringResource(id = R.string.button_delete_account),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.5f)
                    )
                )
            }
        }
    }
}