package com.example.proyectodashboard4.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.proyectodashboard4.pages.Page_Contenidos
import com.example.proyectodashboard4.pages.Page_Flores
import com.example.proyectodashboard4.pages.Page_Frutas_Verduras
import com.example.proyectodashboard4.pages.Page_Huevos
import com.example.proyectodashboard4.pages.Page_Informacion
import com.example.proyectodashboard4.pages.Page_Inicio
import com.example.proyectodashboard4.pages.Page_Lacteos
import com.example.proyectodashboard4.pages.Page_Principal
import com.example.proyectodashboard4.pages.Page_Ver_Mas
import com.example.proyectodashboard4.components.Items_bar
import com.example.proyectodashboard4.components.MenuItem

//controlador de rutas
@Composable
fun Current_Route(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}