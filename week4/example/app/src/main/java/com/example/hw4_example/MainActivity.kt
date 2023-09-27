package com.example.hw4_example

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridView
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var changeActivityBtn: Button

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeActivityBtn = findViewById(R.id.popupMenu);
        changeActivityBtn.setOnClickListener {
            showMenu(it)
        }

        val sharedTextView = findViewById<TextView>(R.id.sharedTextView)

        // Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        val spinnerItems = arrayOf("Item 1", "Item 2", "Item 3")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        // ListView
        val listView = findViewById<ListView>(R.id.listView)
        val listViewItems = arrayOf("Item A", "Item B", "Item C")
        val listViewAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listViewItems)
        listView.adapter = listViewAdapter

        // GridView
        val gridView = findViewById<GridView>(R.id.gridView)
        val gridItems = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        val gridAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, gridItems)
        gridView.adapter = gridAdapter

        // AutoCompleteTextView
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val autoCompleteItems = arrayOf("Apple", "Banana", "Cherry", "Date", "Grape")
        val autoCompleteAdapter =
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, autoCompleteItems)
        autoCompleteTextView.setAdapter(autoCompleteAdapter)

        // Set events
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = spinnerItems[position]
                sharedTextView.text = "Shared Text: $selectedItem"
                Toast.makeText(
                    this@MainActivity,
                    "Selected item: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // call when select current value again
                // sharedTextView.text = "Shared Text: None"
            }
        }
//        spinner.setOnTouchListener { _, event ->
//            if (event.action == MotionEvent.ACTION_DOWN) {
//                sharedTextView.text = "Shared Text: None"
//            }
//            false
//        }

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = listViewItems[position]
            sharedTextView.text = "Shared Text: $selectedItem"
            Toast.makeText(this@MainActivity, "Selected item: $selectedItem", Toast.LENGTH_SHORT)
                .show()
        }

        gridView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = gridItems[position]
            sharedTextView.text = "Shared Text: $selectedItem"
            Toast.makeText(this@MainActivity, "Selected item: $selectedItem", Toast.LENGTH_SHORT)
                .show()
        }

        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = autoCompleteAdapter.getItem(position)
            if (autoCompleteTextView.text.isNullOrEmpty()) {
                sharedTextView.text = "Shared Text: None"
            } else sharedTextView.text = "Shared Text: $selectedItem"
            Toast.makeText(this@MainActivity, "Selected item: $selectedItem", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.change_activity, popup.menu)

        popup.setOnMenuItemClickListener { item ->

            when (item.itemId) {
                R.id.donation_constraint -> {
                    val intent = Intent(this, DonationConstraintActivity::class.java);
                    startActivity(intent);
                    true
                }
                R.id.donation_constraint -> {
                    val intent = Intent(this, EssentialConstraintActivity::class.java);
                    startActivity(intent);
                    true
                }
                else -> false
            }
        }
        popup.show();
    }
}