package com.example.companion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity3 extends BaseActivity  {

    private TextInputLayout pseudo;
    private Button Go;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        pseudo = findViewById(R.id.pseudo);
        Go = findViewById(R.id.button);

        // Initialiser SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        Go.setOnClickListener(v -> entrer());
    }

    private void entrer() {
        String ps = pseudo.getEditText().getText().toString().trim();

        // Récupérer les données depuis SharedPreferences
        String id = sharedPreferences.getString("identifiant", "");
        String mdp = sharedPreferences.getString("password", "");

        if (ps.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer un pseudonyme", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ps.length() < 4) {
            Toast.makeText(this, "Le pseudonyme doit contenir au moins 4 caractères", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Utilisateur nouvelUtilisateur = new Utilisateur(ps,id,mdp);
            // Ajouter toutes les informations à l'utilisateur
            UtilisateurManager.ajouterUtilisateur(nouvelUtilisateur);

            Toast.makeText(this, "Inscription réussie", Toast.LENGTH_SHORT).show();

            // Nettoyer les préférences si nécessaire
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("pseudo", ps);
            editor.apply();

            // Rediriger vers l'activité principale ou de connexion
            startActivity(new Intent(this, MainActivity4.class));
            finish();
        } catch (Exception ex) {
            Toast.makeText(this, "Erreur lors de l'inscription: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}