package com.example.proyectodashboard4.components

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectodashboard4.pages.splash.SplashScreen
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.proyectodashboard4.R
import com.example.proyectodashboard4.pages.Page_Contenidos
import com.example.proyectodashboard4.pages.Page_Flores
import com.example.proyectodashboard4.pages.Page_Frutas_Verduras
import com.example.proyectodashboard4.pages.Page_Huevos
import com.example.proyectodashboard4.pages.Page_Informacion
import com.example.proyectodashboard4.pages.Page_Inicio
import com.example.proyectodashboard4.pages.Page_Lacteos
import com.example.proyectodashboard4.pages.Page_Principal
import com.example.proyectodashboard4.pages.Page_Ver_Mas
import com.example.proyectodashboard4.pages.login.LoginScreen


//cree una clase enum que se encarga de la navegacion
enum class PagesScreen(
    val icon: Int,
    val title: String,
){
    Start(R.drawable.sena, title = "Splash"),
    Login(R.drawable.sena, title = "Login"),
    Tienda(R.drawable.sena, title = "Splash"),
    Principal(R.drawable.bg_tienda_cba, title = "Principal"),
    Flores(R.drawable.ic_flores, title = "Flores"),
    Huevos(R.drawable.ic_huevos, title = "Huevos"),
    FruVer(R.drawable.ic_frutas_verduras, title = "Frutas y Verduras"),
    Leche(R.drawable.ic_lacteos, title = "Lacteos"),
    VerMas(R.drawable.ic_ver_mas, title = "Ver Mas"),


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text("Proyecto_Login", style = MaterialTheme.typography.titleLarge, color = Color.White) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.greenSena)
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}
//Funcion que contiene las navegaciones en un scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp(
    navController: NavHostController = rememberNavController()
){
    val navigationItemsBottomBar = listOf(
        Items_bar.Boton1,
        Items_bar.Boton2,
        Items_bar.Boton3
    )
    val navigationItems = listOf(
        PagesScreen.Principal,
        PagesScreen.FruVer,
        PagesScreen.Huevos,
        PagesScreen.Leche,
        PagesScreen.Flores,
        PagesScreen.VerMas
    )
    val scaffoldState = rememberScaffoldState()
    // scope: utilizada para abrir/cerrar el menu lateral
    val scope = rememberCoroutineScope()
    var index by rememberSaveable {
        mutableStateOf(false)
    }

    val navController = rememberNavController()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if(index){
                TopBar(
                    scope,
                    scaffoldState,
                    navController,
                    navigationItems
                )
            }
            else{}

        },
        drawerContent = {
            DrawerMenu(
                scope,
                scaffoldState,
                navController,
                menu_items = navigationItems
            )
        },
        bottomBar = {
            if(index){
                BottomMenu(
                    navController,
                    menu_items_bar = navigationItemsBottomBar
                )
            }
            else{}

        },
        floatingActionButton = {
            if(index){
                Fab(
                    scope,
                    scaffoldState
                )
            }
            else{}

        },
        isFloatingActionButtonDocked = true
    )
    {
            padding ->
        ScaffoldContent(
            padding = padding
        )
        NavHost(navController = navController, startDestination = PagesScreen.Start.name){
            composable(route = PagesScreen.Start.name){
                SplashScreen(navController, state = false)
            }
            composable(route = PagesScreen.Login.name){
                index = false
                LoginScreen(navController, state = index)
            }
            composable(PagesScreen.Principal.name){
                index = true
                Page_Principal(state = index)
            }
            composable(PagesScreen.Flores.name){
                Page_Flores()
            }
            composable(PagesScreen.FruVer.name){
                Page_Frutas_Verduras()
            }
            composable(PagesScreen.Huevos.name){
                Page_Huevos()
            }
            composable(PagesScreen.Leche.name){
                Page_Lacteos()
            }
            composable(PagesScreen.VerMas.name){
                Page_Ver_Mas()
            }
            composable(Items_bar.Boton1.ruta){
                Page_Inicio()
            }
            composable(Items_bar.Boton2.ruta){
                Page_Contenidos()
            }
            composable(Items_bar.Boton3.ruta){
                Page_Informacion()
            }
        }


    }

}
//muestra el contenido el contenido
@Composable
fun ScaffoldContent(
    padding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = padding.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {

        }
    }
}