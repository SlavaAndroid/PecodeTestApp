package com.android.example.pecodetestapp.util.provider

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.android.example.pecodetestapp.R
import com.android.example.pecodetestapp.ui.main.MainActivity


const val NOTIFICATION_NUMBER_KEY = "number_key"

object NotificationProvider {

    fun createNotification(position: Int, context: Context) {
        createNotificationChannel(position, context)
        val resultIntent = Intent(context, MainActivity::class.java).apply {
            putExtra(
                NOTIFICATION_NUMBER_KEY,
                position
            )
        }
        val resultPendingIntent = PendingIntent.getActivity(
            context, position, resultIntent,
            PendingIntent.FLAG_MUTABLE
        )

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, getChannelId(position))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(
                    String.format(
                        context.getString(R.string.notification_number),
                        position.inc()
                    )
                )
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(position, builder.build())
        }
    }

    fun removeNotification(position: Int, context: Context) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.cancel(position)
    }

    private fun createNotificationChannel(number: Int, context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getChannelId(number)
            val descriptionText = context.getString(R.string.notification_title)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(name, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun getChannelId(number: Int) = "Channel ${number.inc()}"

}