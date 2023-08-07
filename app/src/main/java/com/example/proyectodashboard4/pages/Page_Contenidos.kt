package com.example.proyectodashboard4.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.example.proyectodashboard4.R
import com.example.proyectodashboard4.components.CreateChannelNotification
import com.example.proyectodashboard4.components.notificacionExtensa
import com.example.proyectodashboard4.components.notificacionImagen
import com.example.proyectodashboard4.components.notificacionSencilla

@Composable
fun Page_Contenidos() {
    Column( modifier = Modifier
        .verticalScroll(rememberScrollState())
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(820.dp)
        ) {
            items(
                listatodo
            ){
                    item ->  Cardtodo( imagen = item.imagen, Titulo = item.Titulo, Contenido = item.Contenido, tituloDialogo = item.Titulo2, textoDialogo = item.Contenido2, imagenModal = item.imagen2)
            }
        }
    }

}
@Composable
fun Cardtodo(
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
                    Text(text = tituloDialogo,
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
                            .width(350.dp)
                        ,
                        contentDescription = null)


                    Text(text = textoDialogo, style = TextStyle(fontSize = 15.sp),
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
                                Text("Ubicacion")
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
data class datatodo(

    @DrawableRes val imagen: Int,
    @StringRes val Titulo: Int,
    @StringRes val Contenido: Int,
    val Titulo2: String,
    val Contenido2: String,
    @DrawableRes val imagen2: Int,
)
private val listatodo = listOf(
    datatodo(R.drawable.flores_rosa, R.string.titulo_rosas, R.string.precio_rosas, "Rosas", "Presentando la hermosa flor rosa: delicada, elegante y llena de encanto. Sus pétalos suaves y sedosos se despliegan en una exquisita tonalidad rosada, invitándote a sumergirte en su belleza.", R.drawable.flores_rosa),
    datatodo(R.drawable.flores_clavel, R.string.titulo_clavel, R.string.precio_clavel, "Claveles", "El clavel es una hermosa flor que se caracteriza por su apariencia elegante y fragante. Pertenece a la familia de las Caryophyllaceae y su nombre científico es Dianthus caryophyllus. ", R.drawable.flores_clavel),
    datatodo(R.drawable.flores_girasol, R.string.titulo_girasoles, R.string.precio_girasoles, "Girasoles", "Te presento al majestuoso girasol: una flor que irradia alegría y vitalidad. Con sus grandes y radiantes pétalos amarillos que parecen abrazar al sol, el girasol es un símbolo de energía y optimismo. ", R.drawable.flores_girasol),
    datatodo(R.drawable.huevos_a, R.string.titulo_huevos_a, R.string.precio_huevos_a, "Huevos A", "Los huevos, queridos amigos, son una maravilla de la naturaleza. Son un alimento completo y nutritivo, lleno de proteínas de alta calidad, vitaminas esenciales y minerales beneficiosos.", R.drawable.huevos_a ),
    datatodo(R.drawable.huevos_b, R.string.titulo_huevos_b, R.string.precio_huevos_b, "Huevos B", "Los huevos son una fuente rica de proteínas de alta calidad, vitaminas esenciales y minerales potenciadores para nuestro organismo. Son una excelente opción", R.drawable.huevos_b),
    datatodo(R.drawable.huevos_c, R.string.titulo_huevos_c, R.string.precio_huevos_c, "Huevos C", "Lo que hace que los huevos sean tan especiales es su capacidad para adaptarse a una infinidad de preparaciones culinarias. Desde revueltos y fritos, hasta cocidos y escalfados,", R.drawable.huevos_c),
    datatodo(R.drawable.huevos_aa, R.string.titulo_huevos_aa, R.string.precio_huevos_aa, "Huevos AA", "Los huevos AA, queridos amigos, representan la más alta calidad en la clasificación de los huevos. La designación \"AA\" se refiere a su categoría de frescura y excelencia, ", R.drawable.huevos_aa),
    datatodo(R.drawable.flores_margarita, R.string.titulo_margaritas, R.string.precio_margaritas, "Margaritas", "Te presento a la encantadora margarita: una flor que personifica la sencillez y la belleza natural. Con su distintivo centro amarillo rodeado de pétalos blancos, la margarita irradia pureza y frescura. ", R.drawable.flores_margarita),
    datatodo(R.drawable.flores_lirio, R.string.titulo_lirio, R.string.precio_lirio, "Lirios", "El lirio es una flor majestuosa y elegante que pertenece a la familia Liliaceae. Conocida científicamente como Lilium, esta flor es apreciada por su belleza y aroma cautivador. ", R.drawable.flores_lirio ),
    datatodo(R.drawable.flores_tulipan, R.string.titulo_tulipan, R.string.precio_tulipan, "Tulipanes","El tulipán es una flor icónica y encantadora que pertenece a la familia Liliaceae. Su nombre científico es Tulipa y es nativo de regiones como Europa, Asia Central y el noroeste de África.", R.drawable.flores_tulipan),
    datatodo(R.drawable.cebolla, R.string.titulo_cebolla, R.string.precio_cebolla, "Cebolla", "Esta variedad de cebolla se caracteriza por su piel marrón o amarilla, que protege varias capas de hojas suculentas y crujientes en su interior.", R.drawable.cebolla),
    datatodo(R.drawable.queso, R.string.titulo_queso, R.string.precio_queso, "Queso", "El queso no solo es una delicia para el paladar, sino que también es un ingrediente fundamental en la cocina. Se puede disfrutar solo, en tablas de quesos exquisitos que combinan diferentes variedades.", R.drawable.queso),
    datatodo(R.drawable.leche, R.string.titulo_leche, R.string.precio_leche, "Leche", "Este liquido extraordinario tiene el poder de nutrir y fortalecer nuestro cuerpo. El calcio presente en la leche ayuda a mantener nuestros huesos y dientes sanos y fuertes.", R.drawable.leche),
    datatodo(R.drawable.yogurt, R.string.titulo_yogurt, R.string.precio_yogurt, "Yogurt", "El yogur no solo es bueno para nuestra salud, sino que también es una delicia para nuestro paladar. Viene en una amplia variedad de sabores y texturas, desde el yogur natural sin azúcar.", R.drawable.yogurt),
    datatodo(R.drawable.tomate, R.string.titulo_tomate, R.string.precio_tomate, "Tomate", " es una fruta que se utiliza normalmente como un vegetal en la cocina. Es un ingrediente esencial en diversas culturas y se caracteriza por su forma redondeada u ovalada,", R.drawable.tomate),
    datatodo(R.drawable.cilantro, R.string.titulo_cilantro, R.string.precio_cilantro, "Cilantro", "Las hojas del cilantro tienen un sabor refrescante y cítrico, con toques herbáceos. A menudo se describe como una combinación de perejil y cítricos.", R.drawable.cilantro),
    datatodo(R.drawable.naranja, R.string.titulo_naranja, R.string.precio_naranja, "Naranja", "La pulpa de la naranja es jugosa, dulce y ligeramente ácida, lo que la convierte en un verdadero placer para el paladar. Además de su delicioso sabor.", R.drawable.naranja),
    datatodo(R.drawable.manzana, R.string.titulo_manzana, R.string.precio_manzana, "Manzana", "Además de su delicioso sabor, la manzana es una fuente natural de fibra y buenas vitaminas, como la vitamina C, y contiene antioxidantes que contribuyen a una salud.", R.drawable.manzana),
    datatodo(R.drawable.pina, R.string.titulo_piña, R.string.precio_piña, "Piña", "La piña tiene una apariencia única con su corona de hojas puntiagudas en la parte superior y su piel externa de tonalidad amarilla o dorada.", R.drawable.pina)
)
