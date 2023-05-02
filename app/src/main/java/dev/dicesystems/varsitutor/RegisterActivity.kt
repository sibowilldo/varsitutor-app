package dev.dicesystems.varsitutor

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Badge
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.dicesystems.varsitutor.components.AppVersion
import dev.dicesystems.varsitutor.components.DefaultButton
import dev.dicesystems.varsitutor.components.InputField
import dev.dicesystems.varsitutor.components.ResetPasswordSheetContent
import dev.dicesystems.varsitutor.data.models.RegisterModel
import dev.dicesystems.varsitutor.ui.theme.VarsitutorTheme
import dev.dicesystems.varsitutor.util.PreferenceManager
import dev.dicesystems.varsitutor.util.Resource
import dev.dicesystems.varsitutor.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                CreateRegisterScreen(sheetState = bottomSheetState)
            }

        }
    }
}

@Composable
fun RegisterScreen(content: @Composable () -> Unit) {
    VarsitutorTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            content()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRegisterScreen(
    viewModel: MainViewModel = hiltViewModel(),
    sheetState: SheetState
) {
    RegisterScreen {
        val mutableGivenName = remember { mutableStateOf("") }
        val mutableFamilyName = remember { mutableStateOf("") }
        val mutableContactNumber = remember { mutableStateOf("") }
        val mutableProvinceCity = remember { mutableStateOf("") }
        val mutableInternalIdentification = remember { mutableStateOf("") }
        val mutablePassword = remember { mutableStateOf("") }
        val mutableConfirmPassword = remember { mutableStateOf("") }
        val mutableDeviceId = remember { mutableStateOf("device name not found") }

        val loginState by viewModel.loginState.collectAsState()

        val signInContext = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                try {
                    mutableDeviceId.value =
                        Settings.Secure.getString(
                            signInContext.contentResolver,
                            Settings.Secure.ANDROID_ID
                        )
                } catch (e: Exception) {
                    Toast.makeText(signInContext, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        when (loginState) {
            is Resource.Success -> {
                val token = loginState.data?.token!!
                val sharedPreferencesHelper = PreferenceManager(signInContext)
                sharedPreferencesHelper.saveToken(token)
                signInContext.startActivity(Intent(signInContext, MainActivity::class.java))
            }

            is Resource.Loading -> {
                CircularProgressIndicator()
            }

            is Resource.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.errorContainer,
                            RoundedCornerShape(8.dp)
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Error,
                                contentDescription = "Error Icon",
                                tint = MaterialTheme.colorScheme.onErrorContainer
                            )
                            Text(
                                (loginState as Resource.Error).message!!,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }

            else -> {

            }

        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(verticalArrangement = Arrangement.Top) {
                Column(
                    modifier = Modifier
                        .verticalScroll(
                            state = rememberScrollState()
                        )
                        .fillMaxSize()
                        .padding(18.dp)
                        .padding(bottom = 122.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(id = R.string.heading_create_an_account),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp, start = 8.dp)
                    )

                    Column(
                        modifier = Modifier.padding(vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Fill in this form to create a new account 👍",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                        )


                    }

                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        InputField( // Student Number
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableInternalIdentification,
                            labelId = "Student/Staff Number",
                            keyboardType = KeyboardType.NumberPassword,
                            imeAction = ImeAction.Next,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Badge,
                                    contentDescription = "Badge Icon"
                                )
                            })

                        InputField(//Given Name
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableGivenName,
                            labelId = "Given Name",
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Person2,
                                    contentDescription = "Person Icon"
                                )
                            })

                        InputField( //Family Name
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableFamilyName,
                            labelId = "Family Name",
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.People,
                                    contentDescription = "People Icon"
                                )
                            })

                        InputField( //Contact number
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableContactNumber,
                            labelId = "Contact Number",
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Next,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.PhoneAndroid,
                                    contentDescription = "Phone Icon"
                                )
                            })

                        InputField( // Province & City
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text("KZN, Durban")
                            },
                            valueState = mutableProvinceCity,
                            labelId = "Province & City",
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.LocationCity,
                                    contentDescription = "Phone Icon"
                                )
                            })

                        InputField( // Password
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutablePassword,
                            labelId = "Password",
                            keyboardType = KeyboardType.Password,
                            visualTransformation = PasswordVisualTransformation(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Password,
                                    contentDescription = "Password Icon"
                                )
                            })

                        InputField( // Confirm Password
                            modifier = Modifier.fillMaxWidth(),
                            valueState = mutableConfirmPassword,
                            labelId = "Confirm Password",
                            keyboardType = KeyboardType.Password,
                            visualTransformation = PasswordVisualTransformation(),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Password,
                                    contentDescription = "Password Icon"
                                )
                            })
                        Spacer(modifier = Modifier.size(32.dp))
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        AppVersion()
                    }
                }
            }

            Column(
                Modifier
                    .padding(horizontal = 36.dp)
                    .background(color = MaterialTheme.colorScheme.surface)

            ) {
                DefaultButton(
                    onClick = {
                        viewModel.doRegister(
                            context = signInContext, RegisterModel(
                                internal_identification = mutableInternalIdentification.value,
                                given_name = mutableGivenName.value,
                                family_name = mutableFamilyName.value,
                                contact_number = mutableContactNumber.value,
                                province_city = mutableProvinceCity.value,
                                password = mutablePassword.value,
                                password_confirmation = mutableConfirmPassword.value,
                                device_name = mutableDeviceId.value
                            )
                        )
                    },
                    buttonText = "Sign Up",
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = Color(0xFF000000),
                        disabledContainerColor = Color(
                            android.graphics.Color.rgb(236, 239, 241)
                        ),
                        disabledContentColor = Color(0xFF000000)
                    ),
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = {
                        coroutineScope.launch {
                            sheetState.expand()
                        }
                    }) {
                        Text(
                            "Forgot Password",
                            color = Color.LightGray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    TextButton(onClick = {
                        signInContext.startActivity(
                            Intent(
                                signInContext,
                                LoginActivity::class.java
                            )
                        )
                    }) {
                        Text(
                            "I have an Account",
                            color = Color.LightGray,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
//    CreateRegisterScreen()
}