package com.ahoy.weathertask

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.O
import android.os.Bundle
import com.ahoy.weathertask.presentation.utils.Receiver
import dagger.android.support.DaggerAppCompatActivity
import java.util.*

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        createAlarm()
    }

    private fun createAlarm() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 6)
        calendar.set(Calendar.MINUTE, 50)
        //calendar.set(Calendar.SECOND, 0)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(this, Receiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            receiverIntent,
            0
        )
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= O) {
            val name: CharSequence = "Weather_App"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel =
                NotificationChannel("Weather_App_CHANNEL", name, importance)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}