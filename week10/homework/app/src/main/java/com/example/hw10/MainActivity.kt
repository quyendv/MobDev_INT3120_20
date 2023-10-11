package com.example.hw10

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var editTextEmail: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonUpdate: Button
    private lateinit var buttonDelete: Button
    private lateinit var listViewUsers: ListView
    private lateinit var adapter: SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.editTextEmail)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        buttonDelete = findViewById(R.id.buttonDelete)
        listViewUsers = findViewById(R.id.listViewUsers)

        // Khởi tạo adapter cho ListView
        adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            null,
            arrayOf(UserDatabaseHelper.COLUMN_ID, UserDatabaseHelper.COLUMN_EMAIL),
            intArrayOf(android.R.id.text1, android.R.id.text2),
            0
        )
        listViewUsers.adapter = adapter

        // Init Loader for fetching data from Content Provider
        supportLoaderManager.initLoader(0, null, this)

        buttonAdd.setOnClickListener {
            val email = editTextEmail.text.toString()
            if (email.isNotEmpty()) {
                // add User through Content Provider
                // val values = ContentValues().apply {
                //     put(UserDatabaseHelper.COLUMN_EMAIL, email)
                // }
                // contentResolver.insert(UserContentProvider.CONTENT_URI, values)

                val valuesList = ArrayList<ContentValues>()

                valuesList.add(ContentValues().apply {
                    put(UserDatabaseHelper.COLUMN_EMAIL, "email1")
                })
                valuesList.add(ContentValues().apply {
                    put(UserDatabaseHelper.COLUMN_EMAIL, "email2")
                })
                valuesList.add(ContentValues().apply {
                    put(UserDatabaseHelper.COLUMN_EMAIL, "email3")
                })
                valuesList.add(ContentValues().apply {
                    put(UserDatabaseHelper.COLUMN_EMAIL, "email4")
                })
                valuesList.add (ContentValues().apply {
                    put(UserDatabaseHelper.COLUMN_EMAIL, "email5")
                })

                contentResolver.bulkInsert(UserContentProvider.CONTENT_URI, valuesList.toTypedArray())

                // Clear text EditText
                editTextEmail.text.clear()
            }
        }

        buttonUpdate.setOnClickListener {
            val userId = 1
            val newEmail = editTextEmail.text.toString()
            val selection = "${UserDatabaseHelper.COLUMN_ID} = ?"
            val selectionArgs = arrayOf(userId.toString())

            if (newEmail.isNotEmpty()) {
                val values = ContentValues().apply {
                    put(UserDatabaseHelper.COLUMN_EMAIL, newEmail)
                }

                val updatedRows = contentResolver.update(
                    Uri.withAppendedPath(UserContentProvider.CONTENT_URI, userId.toString()),
                    values,
                    selection,
                    selectionArgs
                )

                if (updatedRows > 0) {
                    Toast.makeText(this, "Update success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Update fail", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty email!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        // create a CursorLoader to fetch data from Content Provider
        val projection = arrayOf(UserDatabaseHelper.COLUMN_ID, UserDatabaseHelper.COLUMN_EMAIL)
        return CursorLoader(this, UserContentProvider.CONTENT_URI, projection, null, null, null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        // update data in adapter when loaded
        adapter.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        // clear data in adapter when reset Loader
        adapter.swapCursor(null)
    }
}