package com.example.proyectodashboard4.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyectodashboard4.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Funcion para disenar el menu desplegable donde va a contener una imagen banner y por cada item de menu se va a llamar el
// drawer item con cada uno de los items
@Composable
fun DrawerMenu(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menu_items: List<PagesScreen>,
) {
    Column() {
        Image(painter = painterResource(id = R.drawable.bg_tienda_cba),
            contentDescription = "Menu de Opciones",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
            )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(15.dp))

        val currentRoute = Current_Route(navController)
        menu_items.forEach {
                item ->
            DrawerItem(
                item = item,
                selected = currentRoute == item.name
            ){
                navController.navigate(item.name){
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        }

    }
}

// Drawer item es la vista de cada uno de los items que se mostraran en el menu desplegable como forma de imagen, filas y titulo de cada uno

@Composable
fun DrawerItem(
    item: PagesScreen,
    selected: Boolean,
    onItemClick: (PagesScreen) ->  Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(
                if (selected) MaterialTheme.colors.primaryVariant.copy(alpha = 0.25f)
                else Color.Transparent
            )
            .padding(8.dp)
            .clickable { onItemClick(item) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = item.icon), contentDescription = item.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = item.title,
        style = MaterialTheme.typography.body1,
        color = if (selected) MaterialTheme.colors.secondaryVariant
        else MaterialTheme.colors.onBackground)
    }
}