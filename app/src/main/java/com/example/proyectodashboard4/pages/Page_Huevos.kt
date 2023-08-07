package com.example.proyectodashboard4.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.proyectodashboard4.R
import com.example.proyectodashboard4.components.CreateChannelNotification
import com.example.proyectodashboard4.components.notificacionExtensa
import com.example.proyectodashboard4.components.notificacionImagen
import com.example.proyectodashboard4.components.notificacionSencilla

@Composable
fun Page_Huevos() {
    Column( modifier = Modifier
        .verticalScroll(rememberScrollState())
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(820.dp)
        ) {
            items(
                listahuevos
            ){
                    item ->  CardHuevos(imagen = item.imagen, Titulo = item.Titulo, Contenido = item.Contenido, tituloDialogo = item.Titulo2, textoDialogo = item.Contenido2, imagenModal = item.imagen2 )
            }
        }
    }
}
@Composable
fun CardHuevos(
    @DrawableRes imagen: Int,
    @StringRes Titulo: Int,
    @StringRes Contenido: Int,
    tituloDialogo: String,
    textoDialogo: String,
    @DrawableRes imagenModal: Int,
){
    //funcion de notificaciones
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idchannel: String = stringResource(R.string.canal_Tienda)
    val name = stringResource(R.string.canal_Tienda)
    val descriptionText= stringResource(R.string.canal_Notifi)

    val textShort: String = "¡Su compra ha sido Exitosa, te esperamos!"
    val textLong: String = "Saludos! Esta es una prueba de notificaciones. Debe aparecer " +
            "un icono a la derecha y el texto puede tener una longitud de 200 caracteres. " +
            "El tamaño de la notificacion puede colapsar y/o expandirse " +
            "Gracias y hasta pronto"
    val iconoBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.ic_contact_mail_black_48dp
    )
    val imagenBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.bg_tienda_cba
    )

    LaunchedEffect(Unit){
        CreateChannelNotification(
            idchannel,
            context,
            name,
            descriptionText
        )
    }
    //funcion de dialogos
    var showDialogHuevos by remember { mutableStateOf(false) }
    if (showDialogHuevos){
        Dialog(
            onDismissRequest = {
                showDialogHuevos = false
            },



            ) {

            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column() {
                    Text(
                        text = tituloDialogo,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .padding(8.dp)
                            .width(300.dp),

                        )
                    Image(
                        painter = painterResource(id = imagenModal),
                        modifier = Modifier
                            .height(300.dp)
                            .width(400.dp),
                        contentDescription = null
                    )


                    Text(
                        text = textoDialogo, style = TextStyle(fontSize = 15.sp),
                        modifier = Modifier
                            .padding(8.dp)
                            .width(350.dp)
                    )

                }
                Row() {
                    TextButton(onClick = {
                        showDialogHuevos = false
                    }) {
                        Text(
                            "Cancelar"
                        )
                    }
                    TextButton(onClick = {
                        showDialogHuevos = false
                        notificacionSencilla(
                            context,
                            idchannel,
                            idNotification,
                            "Notificacion Sencilla",
                            textShort
                        )
                    }) {
                        Text(
                            "Comprar",

                        )
                    }


                }
            }
        }
    }

    //Inicio de cards
    androidx.compose.material3.Card(
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = "flores",
                modifier = Modifier
                    .height(140.dp)
                    .width(350.dp)


            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    stringResource(id = Titulo),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    stringResource(id = Contenido),
                    style = MaterialTheme.typography.titleMedium
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box {
                        Row {
                            TextButton(onClick = {
                                notificacionSencilla(
                                    context,
                                    idchannel,
                                    idNotification,
                                    "Notificacion Sencilla",
                                    textShort
                                )
                            }) {
                                Text(
                                    "Ordenar",

                                )
                            }
                            TextButton(onClick = {
                                showDialogHuevos = true
                            }) {
                                Text(
                                    "Detalles",

                                )
                            }


                        }
                    }
                    Box {
                        Row {
                            androidx.compose.material3.IconButton(onClick = {
                                notificacionImagen(
                                    context,
                                    idchannel,
                                    idNotification + 2,
                                    "Notificación con Imagen",
                                    textLong,
                                    iconoBig,
                                    imagenBig
                                )
                            }) {
                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Boton favorito"
                                )
                            }

                        }

                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center
        )
        {
            androidx.compose.material3.IconButton(onClick = {
                notificacionExtensa(
                    context,
                    idchannel,
                    idNotification + 1,
                    "Notifiación Extensa",
                    textLong,
                    iconoBig
                )


            }) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Boton Compartir"
                )
            }
        }


    }
}
data class datahuevos(
    @DrawableRes val imagen: Int,
    @StringRes val Titulo: Int,
    @StringRes val Contenido: Int,
    val Titulo2: String,
    val Contenido2: String,
    @DrawableRes val imagen2: Int,
)
private val listahuevos = listOf(
    datahuevos(R.drawable.huevos_a, R.string.titulo_huevos_a, R.string.precio_huevos_a, "Huevos A", "Los huevos, queridos amigos, son una maravilla de la naturaleza. Son un alimento completo y nutritivo, lleno de proteínas de alta calidad, vitaminas esenciales y minerales beneficiosos.", R.drawable.huevos_a ),
    datahuevos(R.drawable.huevos_b, R.string.titulo_huevos_b, R.string.precio_huevos_b, "Huevos B", "Los huevos son una fuente rica de proteínas de alta calidad, vitaminas esenciales y minerales potenciadores para nuestro organismo. Son una excelente opción", R.drawable.huevos_b),
    datahuevos(R.drawable.huevos_c, R.string.titulo_huevos_c, R.string.precio_huevos_c, "Huevos C", "Lo que hace que los huevos sean tan especiales es su capacidad para adaptarse a una infinidad de preparaciones culinarias. Desde revueltos y fritos, hasta cocidos y escalfados,", R.drawable.huevos_c),
    datahuevos(R.drawable.huevos_aa, R.string.titulo_huevos_aa, R.string.precio_huevos_aa, "Huevos AA", "Los huevos AA, queridos amigos, representan la más alta calidad en la clasificación de los huevos. La designación \"AA\" se refiere a su categoría de frescura y excelencia, ", R.drawable.huevos_aa),
)