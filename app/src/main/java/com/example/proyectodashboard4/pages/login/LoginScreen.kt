package com.example.proyectodashboard4.pages.login

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.proyectodashboard4.R
import com.example.proyectodashboard4.components.CreateChannelNotification
import com.example.proyectodashboard4.components.PagesScreen
import com.example.proyectodashboard4.components.notificacionExtensa
import com.example.proyectodashboard4.components.notificacionSencilla
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout.RESIZE_MODE_ZOOM
import com.google.android.exoplayer2.ui.StyledPlayerView

//funcion nesesaria que se requiere para el video de fondo
@Composable
fun VideoView(videoUri: String) {
    val context = LocalContext.current

    val exoPlayer = ExoPlayer.Builder(LocalContext.current)
        .build()
        .also { exoPlayer ->
            val mediaItem = MediaItem.Builder()
                .setUri(videoUri)
                .build()
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
            exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
        }

    DisposableEffect(
        AndroidView(factory = {
            StyledPlayerView(context).apply {
                player = exoPlayer
                useController = false
                resizeMode = RESIZE_MODE_ZOOM
            }
        }, modifier = Modifier.fillMaxSize())
    ) {
        onDispose { exoPlayer.release() }
    }
}

@Composable
fun LoginScreen(navController: NavController, state: Boolean){

    val context = LocalContext.current

    val showLoginForm = rememberSaveable {
            mutableStateOf(true)
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            //VideoView es una funcion creada y la URL es tomada como una direccion
            VideoView(videoUri = "android.resource://com.example.proyectodashboard4/raw/sena")


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top

            ) {
                Image(painter = painterResource(
                    id = R.drawable.sena_login,



                ),
                    contentDescription = null,
                            Modifier.size(200.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,

                ) {
                
                Card(modifier = Modifier
                    .alpha(0.8f)
                    .padding(10.dp), backgroundColor = colorResource(id = R.color.white)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,) {
                        if (showLoginForm.value) {

                            //variable para cambio de color
                            val greenOscuro = Color(0xFF248513)


                            Text(text = "Inicia Sesion", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp, color = greenOscuro))
                            UserForm(
                                isCreateAccount = false
                            ) { email, password ->
                                Log.d("TiendaApp", "Inicio sesion con $email y $password")

                                //aqui conecto el login con el dashboard usando el navcontroller
                                navController.navigate(PagesScreen.Principal.name)
                            }
                        } else {
                            //variable para cambio de color
                            val greenOscuro = Color(0xFF248513)

                            Text(text = "Crear Cuenta Nueva", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp, color = greenOscuro))
                            UserForm(
                                isCreateAccount = true
                            ) { email, password ->
                                Log.d("TiendaApp", "Inicio sesion con $email y $password")
                                navController.navigate(PagesScreen.Principal.name)
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            val greenOscuro = Color(0xFF248513)
                            val text1 =
                                if (showLoginForm.value)
                                    "No tienes cuenta?"
                                else
                                    "Ya tienes cuenta?"
                            val text2 =
                                if (showLoginForm.value)
                                    "Registrate"
                                else
                                    "Inicia Sesion"
                            Text(text = text1, style = TextStyle(fontSize = 20.sp))
                            Text(text = text2,
                                modifier = Modifier
                                    .clickable { showLoginForm.value = !showLoginForm.value }
                                    .padding(start = 5.dp), style = TextStyle(fontSize = 20.sp),
                                color = greenOscuro,

                            )
                        }
                    }
                }


            }
        }


}
// Se crean los textfields y se usa la notificacion cuando se presione el boton
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun  UserForm(
    isCreateAccount: Boolean,
    onDone: (String, String) -> Unit = {email, pwd ->},
) {
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = "CanalTienda"
    val name = "CanalTienda"
    val descriptionText = "Canal de Notificaciones"

    val iconoBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.login
    )

    val textShort: String = "Acabas de iniciar sesion "
    val textshort2: String = "Acabas de crear una cuenta"
    val textLong: String = "Saludos! Acabas de seleccionar el producto  como favoritos, sigue explorando mas productos de tu interes"


    LaunchedEffect(Unit){
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val validState = remember(email.value, password.value){
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        EmailInput(
            emailState = email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        Spacer(modifier = Modifier.height(15.dp))
        if(isCreateAccount){
            SubmitButton(
                textId =
                "Crear cuenta",
                inputValid = validState
            ){
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
                notificacionExtensa(
                    context,
                    idChannel,
                    idNotification + 1,
                    textTitle = "Creaste una Cuenta",
                    textContent = textLong,
                    iconoBig,
                    )

            }
        }else{
            SubmitButton(
                textId =
                "Iniciar Sesion",
                inputValid = validState
            ){
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
                notificacionSencilla(
                    context,
                    idChannel,
                    idNotification,
                    "Inicio Sesion",
                    textShort
                )
            }
        }

    }
}
//Diseno para el boton
@Composable
fun SubmitButton(
    textId: String,
    inputValid: Boolean,
    onClic: ()->Unit
) {
    Button(
        onClick = onClic,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValid,
    ) {
        Text(
            text = textId,
            modifier = Modifier
                .padding(5.dp), style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
        )
    }
}
//TextField para la contrasena
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation =
        if (passwordVisible.value)
            VisualTransformation.None
    else
        PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {passwordState.value = it},
        label = { Text(text = labelId)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank())
                PasswordVisibleIcon(passwordVisible)
            else null
        }
    )
}
//Icono para visibilidad de la contrasena
@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if(passwordVisible.value)
            Icons.Default.Visibility
    else
        Icons.Default.VisibilityOff
    IconButton(onClick = {
        passwordVisible.value = !passwordVisible.value
    }) {
        Icon(
            imageVector = image,
            contentDescription = ""
        )
    }
}
// TextField para el email
@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    keyboardType: KeyboardType,
    isSingleLine: Boolean = true
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}