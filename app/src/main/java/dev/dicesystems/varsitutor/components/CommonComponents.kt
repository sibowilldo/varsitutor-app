package dev.dicesystems.varsitutor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.dicesystems.varsitutor.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppVersion() {
    Text(
        "Version 2023.1.0.0",
        color = Color.LightGray,
        style = MaterialTheme.typography.bodyMedium
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(navController: NavController, title: String, showBackIcon: Boolean) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (showBackIcon && navController.previousBackStackEntry != null) {
                run {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        shape = RoundedCornerShape(16.dp),
        value = text,
        trailingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
        },
        placeholder = {
            Text(text = "Search")
        },
        onValueChange = {
            text = it
            onSearch(it)
        },
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.background),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(unfocusedIndicatorColor = MaterialTheme.colorScheme.surface)
    )
}

@Composable
fun TextWithIcon(text: String, textColor: Color, icon: ImageVector, iconDescription: String) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier.size(12.dp)
        )
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            letterSpacing = 0.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResetPasswordSheetContent(
    sheetState: SheetState
) {
    val mutableEmailAddress = remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()

    Box() {
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                stringResource(id = R.string.heading_reset_password),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
            )
            androidx.compose.material.OutlinedTextField(
                keyboardActions = KeyboardActions.Default,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Send
                ),
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("20807032@dut4life.ac.za")
                },
                label = {
                    Text(stringResource(id = R.string.form_email_address))
                },
                value = "", onValueChange = {}
            )
            Text(
                stringResource(R.string.reset_password_helptext),
                modifier = Modifier,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelSmall
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.padding(vertical = 12.dp)
            ) {
                Button(
                    shape = RoundedCornerShape(42.dp),
                    modifier = Modifier.weight(1F),
                    onClick = {
                        mutableEmailAddress.value = ""
                        coroutineScope.launch {
                            sheetState.hide()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF2F2F2),
                        contentColor = Color.DarkGray
                    )
                ){
                    Text(stringResource(id = R.string.button_cancel).uppercase(),)
                }
                Button(
                    shape = RoundedCornerShape(42.dp),
                    modifier = Modifier.weight(1F),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB7FFBE),
                        contentColor = Color.DarkGray
                    )
                ){
                    Text(stringResource(id = R.string.button_confirm).uppercase(),)
                }
            }

        }
    }
}