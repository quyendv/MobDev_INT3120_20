package com.example.hw

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val textView = findViewById<TextView>(R.id.holdContext)
        registerForContextMenu(textView)

        val button = findViewById<Button>(R.id.showPopup)
        button.setOnClickListener {
            showPopupMenu(it)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Context Menu Title")

        menuInflater.inflate(R.menu.context_menu, menu);
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this@MainActivity, "Selected item: ${item.title}", Toast.LENGTH_SHORT).show()

        when (item.itemId) {
            R.id.context_item1 -> {
                return true
            }
            R.id.context_item2 -> {
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this@MainActivity, "Selected item: ${item.title}", Toast.LENGTH_SHORT).show()
        return when (item.itemId) {
            R.id.item1 -> {
                true
            }

            R.id.item2 -> {
                true
            }

            R.id.item3 -> {
                true
            }
            // ...
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            Toast.makeText(this@MainActivity, "Selected item: ${item.title}", Toast.LENGTH_SHORT).show()
            when (item.itemId) {
                R.id.popup_menu_item1 -> {
                    true
                }
                else -> false
            }
        }

        popup.show()
    }

}