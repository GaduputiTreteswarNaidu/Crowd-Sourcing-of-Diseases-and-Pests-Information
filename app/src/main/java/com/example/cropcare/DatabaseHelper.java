package com.example.cropcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and version
    private static final String DATABASE_NAME = "community.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_NAME = "community_questions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FARMER_NAME = "farmer_name";
    private static final String COLUMN_QUESTION = "question";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE = "image";

    // SQL query to create the table
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FARMER_NAME + " TEXT NOT NULL, "
            + COLUMN_QUESTION + " TEXT NOT NULL, "
            + COLUMN_DESCRIPTION + " TEXT NOT NULL, "
            + COLUMN_IMAGE + " BLOB NOT NULL"
            + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    // Called when the database version is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert a new community question into the database
    public long insertCommunityQuestion(String farmerName, String question, String description, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FARMER_NAME, farmerName);
        contentValues.put(COLUMN_QUESTION, question);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_IMAGE, image);

        return db.insert(TABLE_NAME, null, contentValues);
    }

    // Get all community questions from the database
    public Cursor getAllCommunityQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // Get a specific community question by ID
    public Cursor getCommunityQuestionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Update a community question by ID
    public int updateCommunityQuestion(int id, String farmerName, String question, String description, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FARMER_NAME, farmerName);
        contentValues.put(COLUMN_QUESTION, question);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_IMAGE, image);

        return db.update(TABLE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Delete a community question by ID
    public void deleteCommunityQuestion(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
