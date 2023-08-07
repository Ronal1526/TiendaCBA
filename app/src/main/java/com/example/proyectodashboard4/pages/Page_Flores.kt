package com.example.proyectodashboard4.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.proyectodashboard4.R
import com.example.proyectodashboard4.components.CreateChannelNotification
import com.example.proyectodashboard4.components.notificacionExtensa
import com.example.proyectodashboard4.components.notificacionImagen
import com.example.proyectodashboard4.components.notificacionSencilla

@Composable
fun Page_Flores() {


    Column( modifier = Modifier
        .verticalScroll(rememberScrollState())
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(820.dp)
        ) {
            items(
                listaflores
            ){
                    item ->  Cardflor(imagen = item.imagen, Titulo = item.Titulo, Contenido = item.Contenido, tituloDialogo = item.Titulo2, textoDialogo = item.Contenido2, imagenModal = item.imagen2 )
            }
        }
    }


}
@Composable
fun Cardflor(
    @DrawableRes imagen: Int,
    @StringRes Titulo: Int,
    @StringRes Contenido: Int,
    tituloDialogo: String,
    textoDialogo: String,
    @DrawableRes imagenModal: Int,
) {

    //funcion de notificaciones
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idchannel: String = stringResource(R.string.canal_Tienda)
    val name = stringResource(R.string.canal_Tienda)
    val descriptionText= stringResource(R.string.canal_Notifi)

    val textShort: String = "Ejemplo de notificación sencilla con prioridad de omisión (default)"
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
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        Dialog(onDismissRequest = {
            showDialog = false
        }) {
            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
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
                            .height(270.dp)
                            .width(350.dp),
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
                        showDialog = false
                    }) {
                        Text("Cancelar")
                    }
                    TextButton(onClick = {
                        showDialog = false
                        notificacionSencilla(
                            context,
                            idchannel,
                            idNotification,
                            "Notificacion Sencilla",
                            textShort
                        )
                    }) {
                        Text("Comprar")
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
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    stringResource(id = Contenido),
                    style = MaterialTheme.typography.h6
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
                                Text("Ordenar")
                            }
                            TextButton(onClick = {
                                showDialog = true
                            }) {
                                Text("Detalles")
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
data class dataflores(

    @DrawableRes val imagen: Int,
    @StringRes val Titulo: Int,
    @StringRes val Contenido: Int,
    val Titulo2: String,
    val Contenido2: String,
    @DrawableRes val imagen2: Int,


    )
private val listaflores = listOf(
    dataflores(R.drawable.flores_rosa, R.string.titulo_rosas, R.string.precio_rosas, "Rosas", "Presentando la hermosa flor rosa: delicada, elegante y llena de encanto. Sus pétalos suaves y sedosos se despliegan en una exquisita tonalidad rosada, invitándote a sumergirte en su belleza.", R.drawable.flores_rosa),
    dataflores(R.drawable.flores_clavel, R.string.titulo_clavel, R.string.precio_clavel, "Claveles", "El clavel es una hermosa flor que se caracteriza por su apariencia elegante y fragante. Pertenece a la familia de las Caryophyllaceae y su nombre científico es Dianthus caryophyllus. ", R.drawable.flores_clavel),
    dataflores(R.drawable.flores_girasol, R.string.titulo_girasoles, R.string.precio_girasoles, "Girasoles", "Te presento al majestuoso girasol: una flor que irradia alegría y vitalidad. Con sus grandes y radiantes pétalos amarillos que parecen abrazar al sol, el girasol es un símbolo de energía y optimismo. ", R.drawable.flores_girasol),
    dataflores(R.drawable.flores_margarita, R.string.titulo_margaritas, R.string.precio_margaritas, "Margaritas", "Te presento a la encantadora margarita: una flor que personifica la sencillez y la belleza natural. Con su distintivo centro amarillo rodeado de pétalos blancos, la margarita irradia pureza y frescura. ", R.drawable.flores_margarita),
    dataflores(R.drawable.flores_lirio, R.string.titulo_lirio, R.string.precio_lirio, "Lirios", "El lirio es una flor majestuosa y elegante que pertenece a la familia Liliaceae. Conocida científicamente como Lilium, esta flor es apreciada por su belleza y aroma cautivador. ", R.drawable.flores_lirio ),
    dataflores(R.drawable.flores_tulipan, R.string.titulo_tulipan, R.string.precio_tulipan, "Tulipanes","El tulipán es una flor icónica y encantadora que pertenece a la familia Liliaceae. Su nombre científico es Tulipa y es nativo de regiones como Europa, Asia Central y el noroeste de África.", R.drawable.flores_tulipan),

    )



