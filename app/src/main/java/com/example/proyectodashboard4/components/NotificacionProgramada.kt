package com.example.proyectodashboard4.components

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.proyectodashboard4.MainActivity
import com.example.proyectodashboard4.R
import com.example.proyectodashboard4.utils.Constants.channelId

// se realiza la configuracion para la notificacion programada
class NotificacionProgramada: BroadcastReceiver() {

    companion object{
        const val NOTIFICATION_ID = 5
    }

    override fun onReceive(context: Context, intent: Intent?) {
        crearNotification(context)
    }

    private fun crearNotification(context: Context) {

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

        val notificacion = NotificationCompat.Builder(
            context,
            channelId
        )
            .setSmallIcon(R.drawable.informacion)
            .setContentTitle("Tienda CBA")
            .setContentText("Saludos! Nuestra tienda aca de abrir")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Saludos! Tienda Sena esta abierta " +
                            "ahora puedes ver nuestros productos y novedades"+
                            "en nuestra aplicacion puedes verlo "

                    )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager
        manager.notify(
            NOTIFICATION_ID,
            notificacion
        )


    }
}