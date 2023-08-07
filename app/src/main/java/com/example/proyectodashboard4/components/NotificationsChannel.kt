package com.example.proyectodashboard4.components

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.proyectodashboard4.components.NotificacionProgramada.Companion.NOTIFICATION_ID
import java.util.Calendar




fun notificacionProgramada(
    context: Context,
) {
    //Intent describe la actividad que se debe iniciar y contiene los datos necesarios para ello.
    val intent = Intent(
        context,
        NotificacionProgramada::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent: PendingIntent = PendingIntent.getBroadcast(
        context,
        NOTIFICATION_ID,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        Calendar.getInstance().timeInMillis + 10000,
        pendingIntent
    )
}