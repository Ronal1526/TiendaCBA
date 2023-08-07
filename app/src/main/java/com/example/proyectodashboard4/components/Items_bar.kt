package com.example.proyectodashboard4.components
import com.example.proyectodashboard4.R

// se crean los botones para el navegador inferior donde se aplica el icono, titulo y ruta
sealed class Items_bar(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    object Boton1: Items_bar(R.drawable.ic_bike_24, "Inicio", "boton1")
    object Boton2: Items_bar(R.drawable.ic_moto_24, "Contenidos", "boton2")
    object Boton3: Items_bar(R.drawable.ic_recycling_24, "Informacion", "boton3")
}
