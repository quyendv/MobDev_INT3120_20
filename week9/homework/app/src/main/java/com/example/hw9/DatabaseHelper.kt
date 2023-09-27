import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "RandomData.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_RANDOM_DATA = "random_data"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_RANDOM_DATA TEXT)"
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTableSQL = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(dropTableSQL)
        onCreate(db)
    }

    fun insertRandomData(randomData: String): Long {
        val values = ContentValues() // key-value
        values.put(COLUMN_RANDOM_DATA, randomData)
        val db = this.writableDatabase // create interactive instance
        return db.insert(TABLE_NAME, null, values) // null for all column/key of values -> return newId or -1 (-1L)
    }
}
