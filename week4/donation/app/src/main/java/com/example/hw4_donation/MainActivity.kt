package com.example.hw4_donation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        setContentView(R.layout.donation_linear)
//        setContentView(R.layout.donation_relative)
//        setContentView(R.layout.donation_table)
        setContentView(R.layout.donation_constraint)

        val numberPicker = findViewById<NumberPicker>(R.id.picker)
        numberPicker.minValue = 0
        numberPicker.maxValue = 1000
    }
}