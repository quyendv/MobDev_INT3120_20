package com.example.hw11

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class MyForegroundService : Service() {
    private val CHANNEL_ID = "ForegroundServiceChannel"
    private var isServiceRunning = false

    override fun onCreate() {
        super.onCreate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == "START") {
            startForegroundService()
        } else if (intent?.action == "STOP") {
            stopForegroundService()
        }

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startForegroundService() {
        if (!isServiceRunning) {
            createNotificationChannel()
            val notificationIntent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

            val notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Service is running...")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build()

            startForeground(1, notification)

            isServiceRunning = true

            // Start updating notification with random string every 2 seconds
            // val handler = Handler()
            // val runnable = object : Runnable {
            //     override fun run() {
            //         if (isServiceRunning) {
            //             updateNotification()
            //             handler.postDelayed(this, 2000)
            //         }
            //     }
            // }
            // handler.postDelayed(runnable, 2000)
        }
    }

    private fun updateNotification() {
        val randomString = generateRandomString()
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Random String: $randomString")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }

    private fun stopForegroundService() {
        if (isServiceRunning) {
            isServiceRunning = false
            stopForeground(true)
            stopSelf()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Foreground Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    private fun generateRandomString(): String {
        val charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        val randomString = (1..10)
            .map { charPool.random() }
            .joinToString("")
        return randomString
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}