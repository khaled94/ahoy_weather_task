package com.ahoy.weathertask.presentation.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ahoy.weathertask.MainActivity
import com.ahoy.weathertask.R

class Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val notificationIntent = Intent(context, MainActivity::class.java)
        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0,
            notificationIntent, 0)
        //val alarmTitle = String.format("%s Alarm", intent.getStringExtra("Title"))

        //val notificationLayout = RemoteViews(packageName, R.layout.notification_alarm)
        val notification = NotificationCompat.Builder(context!!, "Weather_App_CHANNEL")
            .setContentTitle("Weather App")
            .setSmallIcon(R.drawable.ic_bell)
            .setContentText("Repeating Alarm")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123,notification)
    }
}