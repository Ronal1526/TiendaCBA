package com.example.proyectodashboard4.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// Es el navegador superior que tiene boton para desplegar el menu lateral y tiene otro menu para mostrar idioma y compartir
@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menuItem: List<PagesScreen>
) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    var currentRoute = Current_Route(navController = navController)
    var myTytle = "Tienda Sena CBA"
    menuItem.forEach {
        item -> 
        if(currentRoute == item.name) myTytle = item.title
    }
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = { Text(text = myTytle) },
        navigationIcon = {
            IconButton(onClick = { scope.launch {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }}) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Icono de menu")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Refrescar")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Configurar")
            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Mas Opciones")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false },
            modifier = Modifier.width(150.dp)) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Idioma")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Idioma")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "Compartir")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Compartir")
                }
            }
        }
    )
}