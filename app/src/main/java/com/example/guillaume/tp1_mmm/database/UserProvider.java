package com.example.guillaume.tp1_mmm.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

public class UserProvider extends ContentProvider {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    static final String AUTHORITY = "usercontentprovider";
    public static final String PROVIDER_NAME = "usercontentprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME+ "/users");

    static final String USER_ID = "_id";
    static final String USER_NOM = "nom";
    static final String USER_PRENOM = "prenom";
    static final String USER_VILLE = "ville";
    static final String USER_DATE = "date";
    static final String USER_DEPARTEMENT = "departement";
    static final String USER_TELEPHONE = "telephone";
    public static String[] allColumns = {USER_NOM, USER_PRENOM, USER_VILLE, USER_DATE, USER_DEPARTEMENT, USER_TELEPHONE};

    @Override
    public boolean onCreate() {
        dbHelper = new MySQLiteHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query(MySQLiteHelper.TABLE_USERS, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return ContentResolver.CURSOR_DIR_BASE_TYPE + '/' + "mmm.user";
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = database.insert(MySQLiteHelper.TABLE_USERS, null, values);
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete(MySQLiteHelper.TABLE_USERS, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update(MySQLiteHelper.TABLE_USERS, values, selection, selectionArgs);
    }
}
