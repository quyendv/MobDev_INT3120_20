package com.example.hw10

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class UserContentProvider : ContentProvider() {

    private lateinit var dbHelper: UserDatabaseHelper

    override fun onCreate(): Boolean {
        dbHelper = UserDatabaseHelper(context!!)
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(UserDatabaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
        cursor.setNotificationUri(context!!.contentResolver, uri)
        return cursor
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        val db = dbHelper.writableDatabase
        val id = db.insert(UserDatabaseHelper.TABLE_NAME, null, values)
        context!!.contentResolver.notifyChange(uri, null)
        return ContentUris.withAppendedId(uri, id)
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbHelper.writableDatabase
        val rowsUpdated = db.update(UserDatabaseHelper.TABLE_NAME, values, selection, selectionArgs)
        if (rowsUpdated > 0) {
            context!!.contentResolver.notifyChange(uri, null)
        }
        return rowsUpdated
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = dbHelper.writableDatabase
        val rowsDeleted = db.delete(UserDatabaseHelper.TABLE_NAME, selection, selectionArgs)
        if (rowsDeleted > 0) {
            context!!.contentResolver.notifyChange(uri, null)
        }
        return rowsDeleted
    }

    companion object {
        const val AUTHORITY = "com.example.hw10.usercontentprovider"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/${UserDatabaseHelper.TABLE_NAME}")

        private const val USERS = 1
        private const val USER_ID = 2

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, UserDatabaseHelper.TABLE_NAME, USERS)
            uriMatcher.addURI(AUTHORITY, "${UserDatabaseHelper.TABLE_NAME}/#", USER_ID)
        }
    }
}