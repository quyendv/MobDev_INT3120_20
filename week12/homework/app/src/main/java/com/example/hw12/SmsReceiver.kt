package com.example.hw12

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle: Bundle? = intent?.extras
        if (bundle != null) {
            val pdus = bundle.get("pdus") as Array<*>?
            pdus?.let {
                val messages = arrayOfNulls<SmsMessage>(pdus.size)
                for (i in messages.indices) {
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                }

                val sender = messages[0]?.originatingAddress
                val messageBody = StringBuilder()
                for (message in messages) {
                    messageBody.append(message?.messageBody)
                }

                Toast.makeText(context, "Sms from $sender: $messageBody", Toast.LENGTH_LONG).show()
            }
        }
    }
}



