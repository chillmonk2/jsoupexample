package com.github.chillmonk2.jsoupexample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Database helper for Pets app. Manages database creation and version management.
 */
public class NewsDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = NewsDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "news.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link NewsDbHelper}.
     *
     * @param context of the app
     */
    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the NEWS table
        String SQL_CREATE_NEWS_TABLE = "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + " ("
                + NewsContract.NewsEntry._ID + " TEXT PRIMARY KEY,"
                + NewsContract.NewsEntry.COLUMN_NEWS_STATUS + " INTEGER NOT NULL DEFAULT 1 );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_NEWS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}

