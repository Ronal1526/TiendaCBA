package com.example.proyectodashboard4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.proyectodashboard4.components.TiendaApp
import com.example.proyectodashboard4.ui.theme.ProyectoDashboardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoDashboardTheme {
                // A surface container using the 'background' color from the theme
                TiendaApp()
            }
        }
    }
}

