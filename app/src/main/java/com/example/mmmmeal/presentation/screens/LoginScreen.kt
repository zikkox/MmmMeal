package com.example.mmmmeal.presentation.screens

import android.app.Dialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mmmmeal.R
import com.example.mmmmeal.presentation.navigation.Screen
import com.example.mmmmeal.ui.components.CustomOutlinedTextField
import com.example.mmmmeal.presentation.viewmodels.LoginViewModel

@Composable
fun LoginScreen(navController: NavController) {

    val strawberryRed = colorResource(id = R.color.strawberry_red)
    val lightGray = colorResource(id = R.color.light_gray)

    val snackbarHostState = remember { SnackbarHostState() }

    val viewModel: LoginViewModel = viewModel()
    val state = viewModel.uiState

    val email = state.email
    val password = state.password
    val isPasswordVisible = state.isPasswordVisible

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        //error message host
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) { data ->
            Snackbar(
                containerColor = strawberryRed,
                contentColor = Color.White,
                content = {
                    Text(
                        text = data.visuals.message,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }


        //background image
        Image(
            painter = painterResource(id = R.drawable.bg_food),
            contentDescription = "Food Background",
            modifier = Modifier.fillMaxSize(),
        )

        //main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightGray.copy(alpha = 0f))
                .padding(top = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //top curved background with logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_rectangle),
                    contentDescription = "Top Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    colorFilter = ColorFilter.tint(strawberryRed)
                )

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "Logo",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        "MmmMeal",
                        color = Color.White,
                        fontSize = 36.sp,
                        fontFamily = FontFamily(Font(R.font.baloo_medium)),
                        letterSpacing = 2.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                "Sign In",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.baloo_medium)),
                color = Color.Black,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            //email field
            CustomOutlinedTextField(
                type = "email",
                value = email,
                onValueChangeFun = { viewModel.onEmailChange(it) },
                placeHolder = "Enter your Email",
                isPasswordVisible = false,
                onIconClickFun = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            //password field
            CustomOutlinedTextField(
                type = "password",
                value = password,
                onValueChangeFun = { viewModel.onPasswordChange(it) },
                placeHolder = "Enter your password",
                isPasswordVisible = isPasswordVisible,
                onIconClickFun = { viewModel.togglePasswordVisibility() }
            )


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Forgot password ?",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 40.dp),
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.baloo_medium)),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            //login button
            Button(
                onClick = {
                    viewModel.loginUser()
                },
                colors = ButtonDefaults.buttonColors(containerColor = strawberryRed),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(48.dp)
            ) {
                Text(
                    "Login",
                    fontSize = 24.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.baloo_medium))
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row {
                Text(
                    "No account yet ? ",
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.baloo_medium))
                )
                Text("Sign up now ⓘ",
                    color = strawberryRed,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.baloo_medium)),
                    modifier = Modifier.clickable {
                        navController.navigate("signup")
                    })
            }
        }

        LaunchedEffect(state.successMessage, state.errorMessage) {
            if (state.successMessage != null) {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
            if (!state.errorMessage.isNullOrBlank()) {
                snackbarHostState.showSnackbar(state.errorMessage ?: "null")
                viewModel.clearErrorMessage()
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(strokeWidth = 4.dp)
            }

        }
    }
}


