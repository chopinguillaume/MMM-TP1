package com.example.guillaume.tp1_mmm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.guillaume.tp1_mmm.model.User;

public class ShowInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);

        Bundle b = getIntent().getExtras();

        User user = b.getParcelable("user");

        if(user != null) {
            String prenom = user.getPrenom();
            String nom = user.getNom();
            String fullname = null;
            if (prenom != null && !prenom.isEmpty()) {
                fullname = prenom;
            }
            if (nom != null && !nom.isEmpty()) {
                nom = nom.toUpperCase();
                if (fullname == null) {
                    fullname = nom;
                } else {
                    fullname += " " + nom;
                }
            }
            if (fullname == null) {
                fullname = "Nom inconnu";
            }
            ((TextView) findViewById(R.id.text_prenomnom)).setText(fullname);


            String ville = user.getVille();
            if (ville == null || ville.isEmpty()) {
                ville = "Ville inconnue";
            }
            ((TextView) findViewById(R.id.text_ville)).setText(ville);


            String date = user.getDate();
            if (date == null || date.isEmpty()) {
                date = "Date inconnue";
            }
            ((TextView) findViewById(R.id.text_date)).setText(date);


            String departement = user.getDepartement();
            if (departement == null || departement.isEmpty()) {
                departement = "Département inconnu";
            }
            ((TextView) findViewById(R.id.text_departement)).setText(departement);


            String telephone = user.getTelephone();
            if (telephone == null || telephone.isEmpty()) {
                telephone = "Numéro inconnu";
            }
            ((TextView) findViewById(R.id.text_telephone)).setText(telephone);
        }
    }
}
