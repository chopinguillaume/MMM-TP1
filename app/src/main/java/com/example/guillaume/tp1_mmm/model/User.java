package com.example.guillaume.tp1_mmm.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String prenom;
    private String nom;
    private String ville;
    private String date;
    private String telephone;
    private String departement;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }


    public User() {

    }

    private User(Parcel in) {
        prenom = in.readString();
        nom = in.readString();
        ville = in.readString();
        date = in.readString();
        telephone = in.readString();
        departement = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(prenom);
        dest.writeString(nom);
        dest.writeString(ville);
        dest.writeString(date);
        dest.writeString(telephone);
        dest.writeString(departement);
    }
}
