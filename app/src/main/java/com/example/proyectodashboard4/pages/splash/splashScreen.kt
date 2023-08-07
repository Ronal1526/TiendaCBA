package com.example.proyectodashboard4.pages.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.proyectodashboard4.R
import com.example.proyectodashboard4.components.PagesScreen
import kotlinx.coroutines.delay
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreen(navController: NavController, state: Boolean){
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    val alphaAnim2 = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 4000
        )
    )

    var expanded by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (expanded) 1.2f else 0f, tween(2000))

    LaunchedEffect(key1 = true){
        startAnimation = true
        expanded = true
        delay(5000)
        navController.popBackStack()
        navController.navigate(PagesScreen.Login.name)
    }

    Splash(startAnimation, scale)
}
@Composable
fun Splash(animstate: Boolean, scale: Float){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .scale(scale)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_logo_cba),
                contentDescription = "Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        AnimatedVisibility(
            visible = animstate,
            enter = slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 2000)
            ) + fadeIn(),
            exit = slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(durationMillis = 1000)
            ) + fadeOut()        ) {
            Text(
                text = "",color = Color.White,
                modifier = Modifier.padding(16.dp), style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Bold)
            )
        }
    }


}