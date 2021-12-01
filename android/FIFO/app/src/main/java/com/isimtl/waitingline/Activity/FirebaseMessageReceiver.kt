package com.isimtl.waitingline.Activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.waitbuddy.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessageReceiver : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.notification != null) {
            showNotification(
                remoteMessage.notification!!.title,
                remoteMessage.notification!!.body
            )
        }
    }


    // Method to display the notifications
    fun showNotification(title: String?, message: String?) {
        val ns = Context.NOTIFICATION_SERVICE
        var notificationManager = getSystemService(ns) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "com.example.musicplayer"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationchanne = NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_DEFAULT)
            notificationchanne.description = "Music Player"
            notificationchanne.vibrationPattern = longArrayOf(0L)
            notificationchanne.enableLights(true)
            notificationchanne.lightColor = Color.BLUE
            notificationchanne.setSound(null, null)
            notificationchanne.enableLights(true)
            notificationManager!!.createNotificationChannel(notificationchanne)
        }
        val notificationView = RemoteViews(getPackageName(), R.layout.notification)
        notificationView.setTextViewText(R.id.title, title)
        notificationView.setTextViewText(R.id.message, message)


        var notificationbuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        notificationbuilder!!.setAutoCancel(true)
            .setVibrate(longArrayOf(0L))
            .setOngoing(true)
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setCustomBigContentView(notificationView)
            .setWhen(System.currentTimeMillis())
            .setContentTitle(title)
            .setAutoCancel(false)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        notificationManager!!.notify(0, notificationbuilder!!.build())

    }
}