package com.example.hw8

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val myReceiver = MyReceiver()
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.BROADCAST")

        registerReceiver(myReceiver, intentFilter)

        val broadcastButton = findViewById<Button>(R.id.broadcastButton)
        broadcastButton.setOnClickListener {
            val message = "Hello, this is a custom broadcast message!"

            val intent = Intent("com.example.BROADCAST")
            intent.putExtra("message", message)
            sendBroadcast(intent)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(myReceiver)
        Log.d("MainActivity", "Unregister success by onStop")

    }

    override fun onDestroy() {
        super.onDestroy()
        val message = "Activity was destroyed"
        Log.d("MainActivity", message)
    }
}