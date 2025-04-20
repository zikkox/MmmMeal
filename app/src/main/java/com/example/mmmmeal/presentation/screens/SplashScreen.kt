package com.example.mmmmeal.presentation.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mmmmeal.R
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mmmmeal.presentation.navigation.Screen
import com.example.mmmmeal.viewmodels.SplashViewModel

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = viewModel()) {

    //check for navigating to next screen
    val navigateToHome by viewModel.navigateToHome.collectAsState()

    //colors
    val lemonYellow = colorResource(id = R.color.lemon_yellow)
    val strawberryRed = colorResource(id = R.color.strawberry_red)


    //animation variables
    val scale = remember { Animatable(0f) }
    var textVisible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (textVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "TextAlpha"
    )

    //starts animation upon launch
    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(300)
        textVisible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(lemonYellow, strawberryRed)
                )
            ), contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(200.dp))

            //logo image
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(300.dp)
                    .scale(scale.value)
            )

            //app name text
            Text(
                text = "MmmMeal",
                color = Color.White,
                fontSize = 60.sp,
                fontFamily = FontFamily(Font(R.font.baloo_medium)),
                modifier = Modifier.alpha(alpha)
            )
        }
    }

    LaunchedEffect(navigateToHome) {
        if (navigateToHome) {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }
}
