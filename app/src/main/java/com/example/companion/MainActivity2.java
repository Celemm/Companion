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

public class MainActivity2 extends BaseActivity  {

    private TextInputLayout identifiant, password;
    private Button Suivant;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        identifiant = findViewById(R.id.identifiant);
        password = findViewById(R.id.password);
        Suivant = findViewById(R.id.button);

        // Initialiser SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        Suivant.setOnClickListener(v -> inscrireUtilisateur());
    }

    private void inscrireUtilisateur() {
        String id = identifiant.getEditText().getText().toString().trim();
        String mdp = password.getEditText().getText().toString().trim();

        if (id.isEmpty() || mdp.isEmpty()) {
            Toast.makeText(this, "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mdp.length() < 6) {
            Toast.makeText(this, "Le mot de passe doit contenir au moins 6 caractères", Toast.LENGTH_SHORT).show();
            return;
        }

        // Stocker les données dans SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("identifiant", id);
        editor.putString("password", mdp);
        editor.apply();

        // Passer à l'activité suivante
        startActivity(new Intent(this, MainActivity3.class));
        finish();
    }
}