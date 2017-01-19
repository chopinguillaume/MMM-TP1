package com.example.guillaume.tp1_mmm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.guillaume.tp1_mmm.database.UserDAO;
import com.example.guillaume.tp1_mmm.model.User;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        findViewById(R.id.btn_client).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nouveauClient();
            }
        });

        UserDAO dao = new UserDAO(getApplicationContext());

        Bundle b = getIntent().getExtras();
        if (b != null) {
            User u = b.getParcelable("user");
            if (u != null) {
                dao.createUser(u.getNom(), u.getPrenom(), u.getDate(), u.getVille(), u.getDepartement(), u.getTelephone());
            }
        }

        SimpleAdapter listAdapter = new SimpleAdapter(this, dao.getAllUsers(), R.layout.item,
                new String[]{"nom", "prenom", "date", "ville"},
                new int[]{R.id.text_list_nom, R.id.text_list_prenom, R.id.text_list_date, R.id.text_list_ville});

        ((ListView) findViewById(R.id.list_clients)).setAdapter(listAdapter);

    }

    private void nouveauClient() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
