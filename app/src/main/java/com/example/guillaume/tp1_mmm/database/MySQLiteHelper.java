package com.example.guillaume.tp1_mmm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class MySQLiteHelper extends SQLiteOpenHelper {

    static final String TABLE_USERS = "users";
    static final String USER_ID = "_id";
    static final String USER_NOM = "nom";
    static final String USER_PRENOM = "prenom";
    static final String USER_VILLE = "ville";
    static final String USER_DATE = "date";
    static final String USER_DEPARTEMENT = "departement";
    static final String USER_TELEPHONE = "telephone";

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_USERS + "( " + USER_ID
            + " integer primary key autoincrement, "
            + USER_NOM + " text,"
            + USER_PRENOM + " text,"
            + USER_DATE + " text,"
            + USER_VILLE + " text,"
            + USER_DEPARTEMENT + " text,"
            + USER_TELEPHONE + " text);";

    MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

}
