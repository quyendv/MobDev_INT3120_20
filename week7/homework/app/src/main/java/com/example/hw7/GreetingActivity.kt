package com.example.hw7

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class GreetingActivity : AppCompatActivity() {
    private lateinit var fullName: String
    private lateinit var greetingTextview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actitivy_greeting)

        greetingTextview = findViewById(R.id.greetingMsg)

        val goBack = findViewById<Button>(R.id.back_btn)
        // goBack.setOnClickListener{
        //     val intent = Intent(this, MainActivity::class.java)
        //     startActivity(intent)
        // }
        goBack.setOnClickListener {
            this.onBackPressed()
        }

        val intent = intent // Get intent passed
        fullName = intent.getStringExtra("fullName").toString()
        greetingTextview.text = intent.getStringExtra("message")
    }

    override fun finish() {
        // Prepare data intent
        val data = Intent()
        val feedback = "OK, Hello " + this.fullName + ". How are you?"
        data.putExtra("feedback", feedback)

        // Activity finished ok, return the data
        this.setResult(Activity.RESULT_OK, data)

        super.finish()
    }
}