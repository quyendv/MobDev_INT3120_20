package com.example.hw8

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "com.example.BROADCAST") {
            val message = intent.getStringExtra("message")
            Toast.makeText(context, "Broadcast Received: $message", Toast.LENGTH_SHORT).show()
            Log.d("MyReceiver", "Broadcast Received: $message")
        }
    }
}