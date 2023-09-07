package com.example.welcome_home

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Donation.1.5"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        val numberPicker = findViewById<NumberPicker>(R.id.number_picker)
        numberPicker.minValue = 0
        numberPicker.maxValue = 1000

        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.progress = 30
    }
}