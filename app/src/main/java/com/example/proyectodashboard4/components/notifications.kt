package com.example.proyectodashboard4.components


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.Manifest
import com.example.proyectodashboard4.MainActivity
import com.example.proyectodashboard4.R

//Aqui se encuentra la configuracion de todas las notificaciones
fun CreateChannelNotification(
    idChannel: String,
    context: Context,
    name: String,
    descriptionText: String
) {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        //Definicion del Canal
        val channel = NotificationChannel(
            idChannel,
            name,
            importance
        ).apply {
            description = descriptionText
        }
        //Definicion del Gestor de Notificaciones

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Creacion del Canal
        notificationManager.createNotificationChannel(channel)

    }
}

fun notificacionSencilla(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val intent = Intent(
        context,
        MainActivity::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )
    val builder = NotificationCompat.Builder(
        context,
        idChannel
    )
        .setSmallIcon(R.drawable.login)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    with(NotificationManagerCompat.from(context)){
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(idNotification, builder)
    }
}

fun notificacionExtensa(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    bigIcono: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val intent = Intent(
        context,
        MainActivity::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(
        context,
        idChannel
    )
        .setSmallIcon(R.drawable.login)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(bigIcono)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(textContent)
        )
        .setPriority(priority)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    with(NotificationManagerCompat.from(context)) {
        if(ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notify(idNotification, builder)
    }
}

fun notificacionImagen(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    bigIcono: Bitmap,
    bigImagen: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val intent = Intent(
        context,
        MainActivity::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )
    val builder = NotificationCompat.Builder(
        context,
        idChannel
    )
        .setSmallIcon(R.drawable.login)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(bigIcono)
        .setStyle(
            NotificationCompat
                .BigPictureStyle()
                .bigPicture(bigImagen)
                .setBigContentTitle("Tienda Sena CBA")
        )
        .setPriority(priority)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    with(NotificationManagerCompat.from(context)) {
        if(ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }
        notify(idNotification, builder)
    }
}
