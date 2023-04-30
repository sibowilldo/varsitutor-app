package dev.dicesystems.varsitutor

import android.content.Context
import android.content.Intent
import android.graphics.Color.rgb
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import dev.dicesystems.varsitutor.data.sessions.UserSession
import dev.dicesystems.varsitutor.ui.theme.VarsitutorTheme
import dev.dicesystems.varsitutor.util.PreferenceManager
import dev.dicesystems.varsitutor.util.Resource
import dev.dicesystems.varsitutor.viewmodels.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen() {
                Scaffold() {
                    val scaffoldState = rememberScaffoldState()
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.surface
                    ) {
                        CreateLoginScreen(scaffoldState = scaffoldState)
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(content: @Composable () -> Unit) {
    VarsitutorTheme {
        content()
    }
}

@Composable
fun CreateLoginScreen(
    viewModel: MainViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val mutableEmailAddress = remember { mutableStateOf("21429516@dut4life.ac.za") }
    val mutablePassword = remember { mutableStateOf("5A6oO&9") }
    val mutableDeviceId = remember { mutableStateOf("device name not found") }
    val isLoading by remember { viewModel.isLoading }
    val loginState by viewModel.loginState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            try {
                mutableDeviceId.value =
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .verticalScroll(state = rememberScrollState())
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_dice_vector),
                contentDescription = "DICE Logo",
                modifier = Modifier
                    .height(42.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.CenterStart
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(modifier = Modifier, shape = RoundedCornerShape(8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.sign_image),
                    contentDescription = "Signature",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Hi there! 👋 Welcome Back",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text(
                    "Sign in", style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            }

        }


        when (loginState) {
            is Resource.Success -> {
                val token = loginState.data?.token!!
                val sharedPreferencesHelper = PreferenceManager(context)
                sharedPreferencesHelper.saveToken(token)
                UserSession(user = loginState.data?.user!!)
                context.startActivity(Intent(context, MainActivity::class.java))
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

        Column(
            modifier = Modifier.padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputField(
                modifier = Modifier.fillMaxWidth(),
                valueState = mutableEmailAddress,
                labelId = "Email Address",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Email,
                        contentDescription = "Email Icon"
                    )
                })
            InputField(
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
            Spacer(modifier = Modifier.size(32.dp))
            DefaultButton(
                onClick =
                {
                    viewModel.doLogin(
                        context = context,
                        mutableEmailAddress.value,
                        mutablePassword.value,
                        mutableDeviceId.value
                    )
                },
                buttonText = "Sign In",
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = Color(rgb(236, 239, 241)),
                    disabledContentColor = Color(0xFF000000)
                ),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        "Forgot Password",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                TextButton(onClick = {
                    context.startActivity(Intent(context, RegisterActivity::class.java))
                }) {
                    Text(
                        "Get an Account",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            AppVersion()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    VarsitutorTheme {
        LoginScreen {
//            CreateLoginScreen()
        }
    }
}
