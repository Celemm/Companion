package com.example.companion;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.companion.databinding.ActivityMain4Binding;

public class MainActivity4 extends BaseActivity {

    private ActivityMain4Binding binding;
    private Button btnConnex;
    private Button btnCarte;
    private TextView tvBonjour, tvPseudo;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "UserPrefs"; // corrigé pour matcher MainActivity3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialisation de sharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        tvBonjour = findViewById(R.id.textView6);
        tvPseudo = findViewById(R.id.textContent);
        btnConnex = findViewById(R.id.button4);
        btnCarte = findViewById(R.id.button3);

        String pseudo = getPseudoFromIntentOrPrefs();

        displayPseudo(pseudo);

        setupBottomNavigation();

        btnConnex.setOnClickListener(v -> navigateToActivity(MainActivity5.class, pseudo));
        btnCarte.setOnClickListener(v -> navigateToActivity(MainActivity9.class, pseudo));
    }

    private String getPseudoFromIntentOrPrefs() {
        String pseudo = sharedPreferences.getString("pseudo", "");
        return pseudo;
    }

    private void displayPseudo(String pseudo) {
        if (pseudo != null && !pseudo.isEmpty()) {
            tvBonjour.setText("Bonjour " + pseudo + ",");
            tvPseudo.setText(pseudo + " (vous)");
        } else {
            tvBonjour.setText("Bonjour");
            tvPseudo.setText("");
        }
    }

    private void setupBottomNavigation() {
        binding.bottomNavigationView.setSelectedItemId(R.id.nav_home);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            String pseudo = getPseudoFromIntentOrPrefs();

            if (itemId == R.id.nav_home) {
                return true;
            } else if (itemId == R.id.nav_code) {
                navigateToActivity(MainActivity8.class, pseudo);
                return true;
            } else if (itemId == R.id.emploi) {
                navigateToActivity(MainActivity5.class, pseudo);
                return true;
            } else if (itemId == R.id.nav_profil) {
                navigateToActivity(MainActivity7.class, pseudo);
                return true;
            }
            return false;
        });
    }

    private void navigateToActivity(Class<?> destination, String pseudo) {
        Intent intent = new Intent(this, destination);
        intent.putExtra("PSEUDO", pseudo);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String pseudo = getPseudoFromIntentOrPrefs();
        displayPseudo(pseudo);
    }
}