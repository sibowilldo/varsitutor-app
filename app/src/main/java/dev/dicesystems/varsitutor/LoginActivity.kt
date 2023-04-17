package dev.dicesystems.varsitutor

import android.content.Intent
import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
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
import dev.dicesystems.varsitutor.bottomnavigation.AppNavigation
import dev.dicesystems.varsitutor.components.AppVersion
import dev.dicesystems.varsitutor.components.DefaultButton
import dev.dicesystems.varsitutor.components.InputField
import dev.dicesystems.varsitutor.ui.theme.VarsitutorTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen {
                CreateLoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen(content: @Composable () -> Unit){
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

@Composable
fun CreateLoginScreen() {
    val mutableEmailAddress = remember {
        mutableStateOf("")
    }
    val mutablePassword = remember {
        mutableStateOf("")
    }
    Column(verticalArrangement = Arrangement.Top){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)){
            Image(
                painter = painterResource(id = R.drawable.logo_dice_vector),
                contentDescription = "Signature",
                modifier = Modifier
                    .height(42.dp),
                contentScale = ContentScale.Fit,
                alignment = Alignment.CenterStart
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Surface(modifier = Modifier, shape = RoundedCornerShape(8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.sign_image),
                    contentDescription = "Signature",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Hi there! 👋 Welcome Back",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Text("Sign in", style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center)
            }

        }
        Column(
            modifier = Modifier.padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
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
            val context = LocalContext.current
            DefaultButton(
                onClick =
                {
                    login(true)
                    context.startActivity(Intent(context, MainActivity::class.java))
                },
                buttonText = "Sign In",
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB3E5FC),
                    contentColor = Color(0xFF000000),
                    disabledContainerColor = Color(rgb(236, 239, 241)),
                    disabledContentColor = Color(0xFF000000)
                ),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                TextButton(onClick = { /*TODO*/ }) {
                    Text("Forgot Password",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium)
                }

                TextButton(onClick = {
                    context.startActivity(Intent(context, RegisterActivity::class.java))
                }) {
                    Text("Get an Account",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center){
            AppVersion()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    VarsitutorTheme {
        LoginScreen {
            CreateLoginScreen()
        }
    }
}