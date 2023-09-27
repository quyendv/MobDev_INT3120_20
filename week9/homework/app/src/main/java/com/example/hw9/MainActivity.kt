package com.example.hw9

import DatabaseHelper
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var dataEditText: EditText
    private lateinit var displayTextView: TextView
    private lateinit var sharedPrefsButton: Button
    private lateinit var internalStorageButton: Button
    private lateinit var externalStorageButton: Button
    private lateinit var sqliteButton: Button
    private lateinit var gridView: GridView

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var dataAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataEditText = findViewById(R.id.dataEditText)
        displayTextView = findViewById(R.id.displayTextView)
        internalStorageButton = findViewById(R.id.internalStorageButton)
        externalStorageButton = findViewById(R.id.externalStorageButton)
        sqliteButton = findViewById(R.id.sqliteButton)
        gridView = findViewById(R.id.dataListView)
        sharedPrefsButton = findViewById(R.id.sharedPrefsButton)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        dbHelper = DatabaseHelper(this)
        dataAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        gridView.adapter = dataAdapter


        sharedPrefsButton.setOnClickListener {
            val data = dataEditText.text.toString()
            saveToSharedPreferences(data)
        }

        internalStorageButton.setOnClickListener {
            val data = dataEditText.text.toString()
            saveToInternalStorage(data)
        }

        externalStorageButton.setOnClickListener {
            val data = dataEditText.text.toString()
            saveToExternalStorage(data)
        }

        sqliteButton.setOnClickListener {
            val randomData = generateRandomData()
            val insertedId = dbHelper.insertRandomData(randomData)
            if (insertedId != -1L) { // insert success
                dataAdapter.add(randomData)
                dataAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun saveToSharedPreferences(data: String) {
        val editor = sharedPreferences.edit()
        editor.putString("user_data", data)
        editor.apply()
        displayDataFromSharedPreferences()
    }

    private fun displayDataFromSharedPreferences() {
        val savedData = sharedPreferences.getString("user_data", "")
        displayTextView.text = "Data from SharedPreferences: $savedData"
    }

    private fun saveToInternalStorage(data: String) {
        val fileName = "internal_data.txt"
        val fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
            val outputStreamWriter = OutputStreamWriter(fileOutputStream)
            outputStreamWriter.write(data)
            outputStreamWriter.close()
            displayDataFromInternalStorage(fileName)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun displayDataFromInternalStorage(fileName: String) {
        val fileInputStream: FileInputStream
        try {
            fileInputStream = openFileInput(fileName)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val data = bufferedReader.readText()
            bufferedReader.close()
            displayTextView.text = "Data from Internal Storage: $data"
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun saveToExternalStorage(data: String) {
        val fileName = "external_data.txt"
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED == state) {
            val root = Environment.getExternalStorageDirectory()
            val dir = File(root.absolutePath + "/MyAppFolder")
            if (!dir.exists()) {
                dir.mkdirs()
            }
            val file = File(dir, fileName)
            try {
                if (!file.exists()) {
                    file.createNewFile()
                }
                val fileOutputStream = FileOutputStream(file)
                val outputStreamWriter = OutputStreamWriter(fileOutputStream)
                outputStreamWriter.write(data)
                outputStreamWriter.close()
                displayDataFromExternalStorage(file)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun displayDataFromExternalStorage(file: File) {
        try {
            val fileInputStream = FileInputStream(file)
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val data = bufferedReader.readText()
            bufferedReader.close()
            displayTextView.text = "Data from External Storage: $data"
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun generateRandomData(): String {
        val random = Random()
        return "Random Data ${random.nextInt(100)}"
    }
}