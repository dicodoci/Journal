package app.listview.pedor.com.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Class that takes care of all database operations
public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    // Create table on first time opening app
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Timestamp is created by default
        String sqlCreate = "CREATE TABLE journal ( _id INTEGER PRIMARY KEY, " +
                "title text, content text, mood text, timestamp DATETIME DEFAULT (datetime('now','localtime')));";
        sqLiteDatabase.execSQL(sqlCreate);
    }

    // Delete table when a the database upgrades
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS journal;");
        onCreate(sqLiteDatabase);
    }

    // Constructor
    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Return instance and creates it if it does not exits
    public static EntryDatabase getInstance(Context context) {
        if (EntryDatabase.instance != null) {
            return EntryDatabase.instance;
        } else {
            EntryDatabase.instance = new EntryDatabase(context, "journal", null, 1);
            return EntryDatabase.instance;
        }
    }

    // Return Cursor with entire table
    public static Cursor selectAll(EntryDatabase instance) {
        SQLiteDatabase database = instance.getWritableDatabase();
        return database.rawQuery("SELECT * FROM journal", null);
    }

    // Insert new journal entry in database
    public void insert(JournalEntry entry) {
        SQLiteDatabase database = instance.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("mood", entry.getMood());
        values.put("content", entry.getContent());
        database.insert("journal", null, values);
    }

    // Delete entry from database
    static void delete(long id) {
        SQLiteDatabase db = instance.getWritableDatabase();
        db.delete("Journal", "_id = " + id, null);
    }
}
