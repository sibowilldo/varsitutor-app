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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun VacancyApplyScreen(
    navController: NavController,
    title: String?
)  {
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
                    .padding(16.dp)
                    .padding(bottom = 68.dp)
                    .fillMaxSize()
                    .verticalScroll(
                        state = rememberScrollState()
                    )
            ) {
                Text(
                    text = "Application for $title",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){}
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
fun PreviewVacancyApply() {
    Box(modifier = Modifier, contentAlignment = Alignment.BottomCenter) {
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
                text = "Application for Opening",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
            )


        }
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            DefaultButton(
                onClick = { /*TODO*/ },
                buttonText = stringResource(id = R.string.button_continue),
                textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}

