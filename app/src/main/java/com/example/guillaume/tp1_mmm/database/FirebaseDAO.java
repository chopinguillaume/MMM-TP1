package com.example.guillaume.tp1_mmm.database;

import android.content.Context;
import android.widget.Toast;

import com.example.guillaume.tp1_mmm.UserListAdapter;
import com.example.guillaume.tp1_mmm.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDAO {

    private static final DatabaseReference mDB = FirebaseDatabase.getInstance().getReference();
    private static final DatabaseReference listUserRef = mDB.child("listUser");

    public static void addUser(User user){
        String newKey = listUserRef.push().getKey();
        listUserRef.child(newKey).setValue(user);
    }

    public static void updateOnChanges(final Context context, final UserListAdapter adapter) {
        listUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<User> users = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User u = snapshot.getValue(User.class);
                    users.add(u);
                }

                adapter.updateUserList(users);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String message = "Server error. Refresh page";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
