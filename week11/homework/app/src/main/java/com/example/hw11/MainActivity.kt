package com.example.hw11

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startForegroundButton = findViewById<Button>(R.id.startForegroundButton)
        startForegroundButton.setOnClickListener {
            Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show()

            val serviceIntent = Intent(this, MyForegroundService::class.java)
            serviceIntent.action = "START"
            startService(serviceIntent)
        }

        val stopForegroundButton = findViewById<Button>(R.id.stopForegroundButton)
        stopForegroundButton.setOnClickListener {
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            serviceIntent.action = "STOP"
            startService(serviceIntent)
        }

        // val startBackgroundButton = findViewById<Button>(R.id.startBackgroundButton)
        // startBackgroundButton.setOnClickListener {
        //     startService(backgroundServiceIntent)
        // }

        // val stopBackgroundButton = findViewById<Button>(R.id.stopBackgroundButton)
        // stopBackgroundButton.setOnClickListener {
        //     stopService(backgroundServiceIntent)
        // }
    }
}