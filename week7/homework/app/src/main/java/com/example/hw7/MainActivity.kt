package com.example.hw7

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE = 100
    private lateinit var feedbackText: TextView
    private lateinit var dialBtn: Button
    private lateinit var googleView: Button
    private lateinit var contactView: Button
    private lateinit var imageView: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText);
        feedbackText = findViewById<TextView>(R.id.feedbackMsg)
        dialBtn = findViewById(R.id.dialBtn)
        googleView = findViewById(R.id.google_viewBtn)
        contactView = findViewById(R.id.contactView)
        imageView = findViewById(R.id.imageView)

        val sendMsgBtn = findViewById<Button>(R.id.sendMsgBtn);
        sendMsgBtn.setOnClickListener {
            val intent = Intent(this, GreetingActivity::class.java)
            intent.putExtra("fullName", editText.text.toString()) // Note: pass string, not editText.text
            intent.putExtra("message", "Hello, Please say hello me!")

            // startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE)
        }

        dialBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:123"))
            startActivity(intent)
        }
        googleView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(intent)
        }
        contactView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts//people"))
            startActivity(intent)
        }
        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/pictures/*"
            startActivity(intent)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val message = data?.getStringExtra("feedback")
            feedbackText.text = message
        } else {
            feedbackText.text = "!?"
        }
    }
}
