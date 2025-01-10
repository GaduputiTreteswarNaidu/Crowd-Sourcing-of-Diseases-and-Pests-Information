/*
package com.example.cropcare;

public class HelperClass {

    String name, email, username, password;

    // Default constructor required for Firebase
    public HelperClass() {
    }

    public HelperClass(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
*/

package com.example.cropcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class HelperClass extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "farmers";

    public HelperClass(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB_DEBUG", "onCreate called - Creating table");
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                "farname VARCHAR(50), " +
                "faremail VARCHAR(50), " +
                "farusername VARCHAR(50) PRIMARY KEY, " +
                "farpassword VARCHAR(50))";
        try {
            db.execSQL(createTableQuery);
            Log.d("DB_DEBUG", "Table created successfully");
        } catch (Exception e) {
            Log.e("DB_DEBUG", "Table creation failed", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB_DEBUG", "onUpgrade called - Dropping table and recreating");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRecord(String fname, String femail, String fusername, String fpassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("farname", fname);
        values.put("faremail", femail);
        values.put("farusername", fusername);
        values.put("farpassword", fpassword);

        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1) {
            Log.e("DB_DEBUG", "Insertion failed for username: " + fusername);
            return false;
        } else {
            Log.d("DB_DEBUG", "Insertion successful for username: " + fusername);
            return true;
        }
    }
    public String getPasswordForUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String password = null;

        // Query to get the password for the given username
        Cursor cursor = db.rawQuery("SELECT farpassword FROM farmers WHERE farusername = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            password = cursor.getString(0);  // Get the first column value (password)
        }
        cursor.close();
        db.close();

        return password;  // Return null if username does not exist
    }

    public void logTables() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        if (cursor.moveToFirst()) {
            do {
                Log.d("DB_DEBUG", "Table: " + cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}


