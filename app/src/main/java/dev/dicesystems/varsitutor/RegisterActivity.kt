package dev.dicesystems.varsitutor

import android.content.Intent
import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.dicesystems.varsitutor.components.AppVersion
import dev.dicesystems.varsitutor.components.DefaultButton
import dev.dicesystems.varsitutor.components.InputField
import dev.dicesystems.varsitutor.ui.theme.VarsitutorTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterScreen {
                val mutableGivenName = remember {
                    mutableStateOf("")
                }
                val mutableFamilyName = remember {
                    mutableStateOf("")
                }
                val mutableContactNumber = remember {
                    mutableStateOf("")
                }
                val mutableEmailAddress = remember {
                    mutableStateOf("")
                }
                val mutablePassword = remember {
                    mutableStateOf("")
                }
                val mutableConfirmPassword = remember {
                    mutableStateOf("")
                }
                Column(verticalArrangement = Arrangement.Top) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Surface(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)) {
                            Text(
                                "Create an Account",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Left
                            )
                        }
                        Column(
                            modifier = Modifier.padding(vertical = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Fill in this form to create a new account 👍",
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center,
                            )

                        }
                        Column(
                            modifier = Modifier.padding(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            InputField(
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
                            InputField(
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
                            InputField(
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
                            InputField(
                                modifier = Modifier.fillMaxWidth(),
                                valueState = mutableEmailAddress,
                                labelId = "Email Address",
                                keyboardType = KeyboardType.Text,
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
                            InputField(
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
                            DefaultButton(
                                onClick = { /*TODO*/ },
                                buttonText = "Sign Up",
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(rgb(200, 230, 201)),
                                    contentColor = Color(0xFF000000),
                                    disabledContainerColor = Color(
                                        rgb(
                                            236,
                                            239,
                                            241
                                        )
                                    ),
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
                                val signInContext = LocalContext.current
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
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    RegisterScreen {
        val mutableGivenName = remember {
            mutableStateOf("")
        }
        val mutableFamilyName = remember {
            mutableStateOf("")
        }
        val mutableContactNumber = remember {
            mutableStateOf("")
        }
        val mutableEmailAddress = remember {
            mutableStateOf("")
        }
        val mutablePassword = remember {
            mutableStateOf("")
        }
        val mutableConfirmPassword = remember {
            mutableStateOf("")
        }
        Column(verticalArrangement = Arrangement.Top) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)) {
                    Text(
                        "Create an Account", style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Left
                    )
                }
                Column(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Fill in this form to create a new account 👍",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                    )

                }
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    InputField(
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
                    InputField(
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
                    InputField(
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
                    InputField(
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
                    DefaultButton(
                        onClick = { /*TODO*/ },
                        buttonText = "Sign Up",
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(rgb(200, 230, 201)),
                            contentColor = Color(0xFF000000),
                            disabledContainerColor = Color(
                                rgb(
                                    236,
                                    239,
                                    241
                                )
                            ),
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
                        val signInContext = LocalContext.current
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
    }
}