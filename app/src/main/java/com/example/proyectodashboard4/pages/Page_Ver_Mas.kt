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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectodashboard4.R
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.Dialog
import com.example.proyectodashboard4.components.CreateChannelNotification
import com.example.proyectodashboard4.components.notificacionExtensa
import com.example.proyectodashboard4.components.notificacionImagen
import com.example.proyectodashboard4.components.notificacionSencilla

@Composable
fun Page_Ver_Mas() {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),

        ) {
        androidx.compose.material3.Text(
            text = "Noticias",
            fontWeight = FontWeight.Bold,
            fontSize = 38.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .height(820.dp)
        ) {
            items(
                listaVerMas
            ){
                    item ->  CardVermas(imagen = item.imagen, Titulo = item.Titulo, Contenido = item.Contenido, tituloDialogo = item.Titulo2, textoDialogo = item.Contenido2, imagenModal = item.imagen2 )
            }
        }
    }
}
@Composable
fun CardVermas(
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
                            "Notificar",

                            )
                    }


                }
            }
        }
    }

    //Inicio de cards
    androidx.compose.material3.Card(
        modifier = Modifier
            .clickable { showDialogHuevos = true}
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = imagen),
                contentDescription = "flores",
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()



            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    stringResource(id = Titulo),
                    style =TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
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
                            IconButton(onClick = {
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
                                Icon(
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
            IconButton(onClick = {
                notificacionExtensa(
                    context,
                    idchannel,
                    idNotification + 1,
                    "Notifiación Extensa",
                    textLong,
                    iconoBig
                )


            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Boton Compartir"
                )
            }
        }


    }
}
data class datavermas(
    @DrawableRes val imagen: Int,
    @StringRes val Titulo: Int,
    @StringRes val Contenido: Int,
    val Titulo2: String,
    val Contenido2: String,
    @DrawableRes val imagen2: Int,
)
private val listaVerMas = listOf(
    datavermas(R.drawable.carrusel_1, R.string.cumpleaños_sena, R.string.texto_sena, "Cumpleaños sena", "El Servicio Nacional de Aprendizaje, más conocido como SENA, es una entidad pública en Colombia que se encarga de la formación técnica, tecnológica y profesional de los ciudadanos", R.drawable.carrusel_1 ),
    datavermas(R.drawable.vermas_1, R.string.nuestra_Tienda, R.string.nuestra_tienda, "Nuestra tienda", "¡Gran noticia! ¡Tenemos un descuento especial para ti en nuestra tienda virtual! Por tiempo limitado, te ofrecemos un increíble descuento del 20% en todos nuestros productos.", R.drawable.vermas_1),
    datavermas(R.drawable.vermas_2, R.string.Dia_de_Descuentos, R.string.texto_descuento, "Ofertas", "¡Bienvenidos a nuestra tienda virtual campesina, donde la esencia de la tierra y la tradición se encuentran en cada producto que ofrecemos! Nos enorgullecemos de ofrecerte lo mejor", R.drawable.vermas_2),
)
