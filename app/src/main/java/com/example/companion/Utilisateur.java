package com.example.companion;

public class Utilisateur {
    private String pseudo;
    private String identifiant;
    private String password;

    public Utilisateur(String pseudo, String identifiant, String password){

        this.pseudo = pseudo;
        this.identifiant = identifiant;
        this.password = password;
    }



    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String Pseudo) {

        this.pseudo = pseudo;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {

        this.identifiant = identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}



