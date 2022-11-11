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

       // createNotificationChannel()
       // createAlarm()
    }
}