package com.example.appmpazpro2.utils

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

private const val CANAL_ID = "revision_contenido"

fun mostrarNotificacion(
    context: Context,
    titulo: String,
    mensaje: String
) {

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

        val canal = NotificationChannel(
            CANAL_ID,
            "Revision de contenidos",
            NotificationManager.IMPORTANCE_HIGH
        )

        canal.description =
            "Notificaciones de aprobacion y rechazo de contenidos"

        notificationManager.createNotificationChannel(canal)
    }

    if (
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
        context.checkSelfPermission(
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return
    }

    val notificacion = if (
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    ) {

        Notification.Builder(
            context,
            CANAL_ID
        )

    } else {

        Notification.Builder(context)
    }
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle(titulo)
        .setContentText(mensaje)
        .setAutoCancel(true)
        .build()

    notificationManager.notify(
        System.currentTimeMillis().toInt(),
        notificacion
    )
}