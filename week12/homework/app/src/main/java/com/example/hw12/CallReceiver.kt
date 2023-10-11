package com.example.hw12

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        if (action != null && action == "android.intent.action.PHONE_STATE") {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (state != null && state == TelephonyManager.EXTRA_STATE_RINGING) {
                val phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                Toast.makeText(context, "Call from $phoneNumber", Toast.LENGTH_LONG).show()
            }
        }
    }
}