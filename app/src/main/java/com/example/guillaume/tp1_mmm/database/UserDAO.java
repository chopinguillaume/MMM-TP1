package com.example.guillaume.tp1_mmm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.guillaume.tp1_mmm.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {

    private Context context;
    private Uri allUsers = UserProvider.CONTENT_URI;

    public UserDAO(Context context) {
        this.context = context;
    }

    public User createUser(String nom, String prenom, String date, String ville, String departement, String telephone) {
        ContentValues values = new ContentValues();

        values.put(UserProvider.USER_NOM, nom);
        values.put(UserProvider.USER_PRENOM, prenom);
        values.put(UserProvider.USER_DATE, date);
        values.put(UserProvider.USER_VILLE, ville);
        values.put(UserProvider.USER_DEPARTEMENT, departement);
        values.put(UserProvider.USER_TELEPHONE, telephone);

        Uri uriNewUser = context.getContentResolver().insert(allUsers, values);

        Cursor c = context.getContentResolver().query(uriNewUser, null, null, null, null);

        c.moveToFirst();
        return cursorToUser(c);
    }

    public List<Map<String,String>> getAllUsers() {
        Cursor c = context.getContentResolver().query(allUsers, null, null, null, null);
        List<Map<String,String>> users = new ArrayList<>();

        if (c != null && c.moveToFirst()) {
            do {
                User u = cursorToUser(c);
                Map<String, String> map = new HashMap<>();

                map.put(UserProvider.USER_NOM, u.getNom());
                map.put(UserProvider.USER_PRENOM, u.getPrenom());
                map.put(UserProvider.USER_DATE, u.getDate());
                map.put(UserProvider.USER_VILLE, u.getVille());
                map.put(UserProvider.USER_DEPARTEMENT, u.getDepartement());
                map.put(UserProvider.USER_TELEPHONE, u.getTelephone());

                users.add(map);
            } while (c.moveToNext());
        }
        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();

        user.setNom(cursor.getString(cursor.getColumnIndex(UserProvider.USER_NOM)));
        user.setPrenom(cursor.getString(cursor.getColumnIndex(UserProvider.USER_PRENOM)));
        user.setDate(cursor.getString(cursor.getColumnIndex(UserProvider.USER_DATE)));
        user.setVille(cursor.getString(cursor.getColumnIndex(UserProvider.USER_VILLE)));
        user.setDepartement(cursor.getString(cursor.getColumnIndex(UserProvider.USER_DEPARTEMENT)));
        user.setTelephone(cursor.getString(cursor.getColumnIndex(UserProvider.USER_TELEPHONE)));

        return user;
    }
}

