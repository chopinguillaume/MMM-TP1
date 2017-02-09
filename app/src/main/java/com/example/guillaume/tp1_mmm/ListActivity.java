package com.example.guillaume.tp1_mmm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

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

        final UserDAO dao = new UserDAO(getApplicationContext());

        Bundle b = getIntent().getExtras();
        if (b != null) {
            User u = b.getParcelable("user");
            if (u != null) {
                dao.createUser(u.getNom(), u.getPrenom(), u.getDate(), u.getVille(), u.getDepartement(), u.getTelephone());
            }
        }

        final UserListAdapter userListAdapter = new UserListAdapter(this, dao.getAllUsers());

        ((ListView) findViewById(R.id.list_clients)).setAdapter(userListAdapter);

        ((ListView) findViewById(R.id.list_clients)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User u = userListAdapter.getItem(position);
                Intent i = new Intent(getApplicationContext(), ShowInformation.class);
                i.putExtra("user", u);
                startActivity(i);
            }
        });

        ((EditText) findViewById(R.id.text_filter)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userListAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void nouveauClient() {
        Intent i = new Intent(getApplicationContext(), NewUserActivity.class);
        startActivity(i);
        finish();
    }
}
