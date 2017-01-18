package com.example.guillaume.tp1_mmm;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.guillaume.tp1_mmm.model.User;

public class MainActivity extends AppCompatActivity {

    private EditText edit_tel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_valider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valider(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_tel:
                ajouter_telephone();
                return true;
            case R.id.menu_raz:
                remise_a_zero();
                return true;
            case R.id.menu_wiki:
                wikipedia();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void valider(View view) {
        Intent i = new Intent(getApplicationContext(), ShowInformation.class);
        User user = new User();

        String prenom = ((EditText) findViewById(R.id.edit_prenom)).getText().toString();
        String nom = ((EditText) findViewById(R.id.edit_nom)).getText().toString();
        String date = ((EditText) findViewById(R.id.edit_date)).getText().toString();
        String ville = ((EditText) findViewById(R.id.edit_ville)).getText().toString();
        String departement = ((Spinner) findViewById(R.id.spinner_depart)).getSelectedItem().toString();
        if (edit_tel != null) {
            String telephone = edit_tel.getText().toString();
            user.setTelephone(telephone);
        }

        user.setPrenom(prenom);
        user.setNom(nom);
        user.setDate(date);
        user.setDepartement(departement);
        user.setVille(ville);

        i.putExtra("user", user);
        startActivity(i);
    }

    private void wikipedia() {
        EditText edit_ville = (EditText) findViewById(R.id.edit_ville);
        String ville = edit_ville.getText().toString();
        if (ville.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Merci de renseigner la ville de naissance", Toast.LENGTH_SHORT).show();
        } else {
            Intent wiki_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://fr.wikipedia.org/?search=" + ville));
            startActivity(wiki_intent);
        }
    }

    private void remise_a_zero() {
        new AlertDialog.Builder(this)
                .setTitle("Remise à zéro")
                .setMessage("Êtes-vous sûr(e) de vouloir effacer les informations ?")
                .setNegativeButton("Annuler", null)
                .setPositiveButton("Effacer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final EditText edit_nom = (EditText) findViewById(R.id.edit_nom);
                        final EditText edit_prenom = (EditText) findViewById(R.id.edit_prenom);
                        final EditText edit_date = (EditText) findViewById(R.id.edit_date);
                        final EditText edit_ville = (EditText) findViewById(R.id.edit_ville);

                        edit_nom.setText("");
                        edit_prenom.setText("");
                        edit_date.setText("");
                        edit_ville.setText("");
                    }
                }).show();
    }

    private void ajouter_telephone() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_champs);
        if (edit_tel == null) {
            edit_tel = new EditText(this);
            edit_tel.setHint("Numéro de téléphone");
            edit_tel.setInputType(InputType.TYPE_CLASS_PHONE);
            layout.addView(edit_tel);
        }
    }
}
